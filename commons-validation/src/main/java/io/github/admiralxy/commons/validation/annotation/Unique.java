package io.github.admiralxy.commons.validation.annotation;

import io.github.admiralxy.commons.validation.validator.UniqueConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for enforcing unique constraints on a field or a set of fields in the database.
 * This annotation is used to ensure that the specified field(s) have unique values across the table.
 *
 * <p>Example usage:</p>
 *
 * <pre>
 * {@literal @}Unique(entity = "UserJpaEntity", fields = {"email"})
 *  public class UserDto {
 *      private String email;
 *  }
 * </pre>
 */
@Documented
@Constraint(validatedBy = UniqueConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "{commons.validation.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String id() default "id";

    String entity();

    String[] fields() default {};

    boolean postfix() default false;
}
