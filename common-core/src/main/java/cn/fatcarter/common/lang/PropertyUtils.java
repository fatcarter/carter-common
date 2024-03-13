package cn.fatcarter.common.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {

    public static Map<String, Object> toMap(Properties properties) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (String name : properties.stringPropertyNames()) {
            map.put(name, properties.get(name));
        }
        return map;
    }

    public static Properties loadFile(String classpath) throws IOException {
        return loadFile(null, classpath);
    }

    public static Properties loadFile(Properties properties, String classpath) throws IOException {
        if (properties == null) {
            properties = new Properties();
        }
        InputStream io = PropertyUtils.class.getClassLoader().getResourceAsStream(classpath);
        if (io == null) {
            throw new IOException("File not found: " + classpath);
        }
        properties.load(io);
        return properties;
    }
}
