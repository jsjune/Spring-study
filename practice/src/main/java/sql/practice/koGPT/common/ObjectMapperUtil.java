package sql.practice.koGPT.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Component
public class ObjectMapperUtil<T> {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(@NotNull Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static MultiValueMap<String, String> convertValue(@NotNull Object object) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> map = objectMapper.convertValue(object, new TypeReference<Map<String, String>>() {});
            params.setAll(map);
            return params;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T koGptReadValue(@NotNull String json, Class<T> t)
        throws IOException {
        T target = objectMapper.readValue(json, t);;
        return target;
    }

    public static String mapToString(Map<?, ?> map) {
        try {
            return objectMapper.writeValueAsString(map);
        }
        catch (JsonProcessingException e) {
            return null;
        }
    }
}
