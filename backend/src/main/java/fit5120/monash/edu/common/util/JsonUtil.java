package fit5120.monash.edu.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * AI generated Features to convert data into List<String> format.
     * 将字符串转换为 List<String>
     */
    public static List<String> toList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        input = input.trim();

        // 👉 情况1：看起来像 JSON 数组
        if (input.startsWith("[") && input.endsWith("]")) {
            try {
                // 处理单引号问题
                String json = normalizeJsonArray(input);
                return objectMapper.readValue(json, new TypeReference<List<String>>() {});
            } catch (Exception e) {
                // fallback
                return parseManually(input);
            }
        }

        // 👉 情况2：普通字符串
        return parseManually(input);
    }


    private static List<String> parseManually(String input) {
        // 去掉 []
        if (input.startsWith("[") && input.endsWith("]")) {
            input = input.substring(1, input.length() - 1);
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(s -> s.replace("'", "").replace("\"", ""))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * 将单引号数组转换为合法 JSON
     */
    private static String normalizeJsonArray(String input) {
        // 如果已经是双引号，直接返回
        if (input.contains("\"")) {
            return input;
        }
        // 替换单引号为双引号
        return input.replace("'", "\"");
    }




    // object to json
    public static String toJson(Object obj) {
        if (obj == null) return null;

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("object convert json fails", e);
        }
    }

    // JSON → object
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON convert object fails", e);
        }
    }

    // JSON → Map
    public static Map<String, Object> toMap(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON convert map fails", e);
        }
    }

}
