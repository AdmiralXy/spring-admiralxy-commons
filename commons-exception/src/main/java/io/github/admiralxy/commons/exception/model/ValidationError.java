package io.github.admiralxy.commons.exception.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ValidationError {
    private Map<String, List<String>> fields = new HashMap<>();

    public static ValidationError of(String field, String error) {
        return of(field, Collections.singletonList(error));
    }

    public static ValidationError of(String field, List<String> errorList) {
        return of(Collections.singletonMap(field, errorList));
    }

    public static ValidationError of(Map<String, List<String>> errorMap) {
        ValidationError response = new ValidationError();
        response.fields.putAll(errorMap);
        return response;
    }
}
