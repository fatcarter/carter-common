package cn.fatcarter.common.serialize;

import cn.fatcarter.common.serialize.exception.SerializeException;
import cn.fatcarter.common.serialize.jackson.JacksonArray;
import cn.fatcarter.common.serialize.jackson.JacksonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.List;
import java.util.function.Consumer;

public class Jackson {
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    public static void customizeObjectMapper(Consumer<ObjectMapper> customize) {
        customize.accept(objectMapper);
    }

    public static String toJSONString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new SerializeException(e);
        }
    }


    public static JacksonObject parseObject(String json) {
        return parseObject(json, JacksonObject.class);
    }

    public static JacksonArray parseArray(String json) {
        return new JacksonArray(parseArray(json, JacksonObject.class));
    }

    public static <T> List<T> parseArray(String json, Class<T> clz) {
        try {
            return objectMapper.readerForListOf(clz)
                    .readValue(json);
        } catch (JsonProcessingException e) {
            throw new SerializeException(e);
        }
    }

    public static <T> T parseObject(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, clz);
        } catch (JsonProcessingException e) {
            throw new SerializeException(e);
        }
    }

    public static <T> T parseObject(String json, TypeReference<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new SerializeException(e);
        }
    }

    public static <T> T parseObject(String json, JavaType type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new SerializeException(e);
        }
    }
}
