package cn.fatcarter.common.serialize.jackson;

import cn.fatcarter.common.serialize.Jackson;
import cn.fatcarter.common.serialize.exception.SerializeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class JacksonObject extends LinkedHashMap<String, Object> {
    public JacksonObject(){
    }
    public JacksonObject(Map<? extends String, ?> m) {
        super(m);
    }

    public String getString(String key) {
        return getValue(key, String.class, null);
    }

    public Long getLong(String key) {
        return getValue(key, Long.class, Long::parseLong);
    }

    public Integer getInteger(String key) {
        return getValue(key, Integer.class, Integer::parseInt);
    }

    public JacksonObject getObject(String key) {
        Map<String, Object> value = this.getValue(key, Map.class, null);
        return new JacksonObject(value);
    }

    public <T> T getValue(String key, Class<T> type, Function<String, T> convert) {
        Object value = this.getOrDefault(key, null);
        if (value == null) {
            return null;
        }
        if (type.isAssignableFrom(value.getClass())) {
            return (T) value;
        }
        if (value instanceof String) {
            if (convert == null) {
                return (T) value;
            }
            try {
                return convert.apply((String) value);
            } catch (Throwable throwable) {
                throw new SerializeException("Value " + value + " can't convert to " + type + " type");
            }
        } else if (convert != null) {
            return convert.apply(value.toString());
        }

        throw new SerializeException("Value " + value + " can't convert to " + type + " type");
    }

    public String toJSONString() {
        return Jackson.toJSONString(this);
    }
}
