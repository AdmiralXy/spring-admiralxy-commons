package io.github.admiralxy.commons.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.admiralxy.commons.exception.dictionary.NotificationType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MsExceptionTest {

    private static final String FIELD_1 = "fieldName1";
    private static final String FIELD_2 = "fieldName2";
    private static final String MESSAGE = "message";
    private static final String UUID_FIELD = "uuid";
    private static final String TIMESTAMP_FIELD = "timestamp";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ParameterizedTest
    @MethodSource("testMsExceptionDataProvider")
    void testMsException(String expectedJsonFilepath, MsException e) throws JsonProcessingException {
        JsonNode expectedJsonNode = objectMapper.readTree(readResourceFile("exceptions/" + expectedJsonFilepath));
        JsonNode actualJsonNode = objectMapper.readTree(getExceptionBody(e));

        assertEquals(expectedJsonNode, actualJsonNode);
    }

    private static Stream<Arguments> testMsExceptionDataProvider() {
        return Stream.of(
                Arguments.of("access_denied.json", new MsAccessDeniedException()),
                Arguments.of("business.json", new MsBusinessException(MESSAGE)),
                Arguments.of("not_found.json", new MsNotFoundException()),
                Arguments.of("notification.json", new MsNotificationException(NotificationType.INFO, MESSAGE)),
                Arguments.of("technical.json", new MsTechnicalException()),
                Arguments.of("validation.json", new MsValidationException(getValidationErrorMap()))
        );
    }

    private static Map<String, List<String>> getValidationErrorMap() {
        return Map.of(
                FIELD_1, List.of(MESSAGE, MESSAGE),
                FIELD_2, List.of(MESSAGE, MESSAGE, MESSAGE)
        );
    }

    private String readResourceFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            assert is != null;
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getExceptionBody(MsException msException) {
        try {
            var exceptionBody = objectMapper.writeValueAsString(msException.getBody());
            var node = objectMapper.readTree(exceptionBody);
            ((ObjectNode) node).remove(UUID_FIELD);
            ((ObjectNode) node).remove(TIMESTAMP_FIELD);
            return node.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
