package com.admiralxy.commons.validation.validator;

import com.admiralxy.commons.validation.annotation.Unique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {

    private static final String GLOBAL_ENTITY_POSTFIX = "JpaEntity";

    private String id;
    private String entity;
    private String[] fields;
    private boolean postfix;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void initialize(Unique constraintAnnotation) {
        id = constraintAnnotation.id();
        entity = constraintAnnotation.entity();
        fields = constraintAnnotation.fields();
        postfix = constraintAnnotation.postfix();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (entity == null) {
            return false;
        }

        Class<?> clazz = value.getClass();
        try {
            Object idValue = FieldUtils.readField(value, id, true);

            Map<String, Object> fieldValues = Arrays.stream(fields)
                    .map(field -> {
                        try {
                            Field f = FieldUtils.getField(clazz, field, true);
                            Object fieldValue = FieldUtils.readField(f, value, true);

                            if (fieldValue == null) {
                                return null;
                            }
                            return Map.entry(field, fieldValue);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            if (fieldValues.isEmpty()) {
                return true;
            }

            String entityName = StringUtils.capitalize(entity);
            if (postfix) {
                entityName = entity + GLOBAL_ENTITY_POSTFIX;
            }

            String query = getQuery(entityName, fieldValues, id, idValue);

            Query queryBuilder = entityManager.createQuery(query);

            if (idValue != null) {
                queryBuilder = queryBuilder.setParameter(id, idValue);
            }

            for (var entry : fieldValues.entrySet()) {
                queryBuilder = queryBuilder.setParameter(entry.getKey(), entry.getValue());
            }

            if ((Long) queryBuilder.getSingleResult() != 0) {
                String messageTemplate = context.getDefaultConstraintMessageTemplate();
                context.disableDefaultConstraintViolation();

                for (var entry : fieldValues.entrySet()) {
                    context.buildConstraintViolationWithTemplate(messageTemplate)
                            .addPropertyNode(entry.getKey())
                            .addConstraintViolation();
                }

                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private String getQuery(String entityName, Map<String, Object> fieldValues, String id, Object idValue) {
        var query = new StringBuilder("SELECT COUNT(*) FROM " + entityName + " WHERE (");

        var conditions = fieldValues.keySet().stream()
                .map(o -> o + " = :" + o)
                .toList();

        query.append(String.join(" OR ", conditions));

        query.append(")");

        if (idValue != null) {
            query.append(" AND ").append(id).append(" != :").append(id);
        }

        return query.toString();
    }
}
