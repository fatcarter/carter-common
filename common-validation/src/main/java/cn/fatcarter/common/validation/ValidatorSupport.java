package cn.fatcarter.common.validation;

import java.util.List;
import java.util.Map;

public class ValidatorSupport {

    protected boolean accept(Object value, List<String> acceptValues) {
        if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            for (Object o : map.keySet()) {
                if (!(o instanceof String) && o != null) {
                    o = String.valueOf(o);
                }
                boolean accept = acceptValues.contains(o);
                if (!accept) {
                    return false;
                }
            }
            return true;
        }
        if (value == null) return true;
        if (!(value instanceof String)) {
            value = String.valueOf(value);
        }
        return acceptValues.contains(value);
    }
}
