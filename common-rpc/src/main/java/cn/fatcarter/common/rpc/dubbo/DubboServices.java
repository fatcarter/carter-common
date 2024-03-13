package cn.fatcarter.common.rpc.dubbo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.dubbo.config.ReferenceConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DubboServices {
    private static final String DUBBO_REFERENCE_ID_PREFIX = DubboServices.class.getName() + "#";
    private static final ConcurrentMap<String /* key */, DubboService<?>> DUBBO_SERVICE_CONCURRENT_MAP = new ConcurrentHashMap<>();

    public static <T> T getService(Class<T> interfaceClz) {
        return getService(DubboDefinition.builder().interfaceName(interfaceClz.getName()).build());
    }

    public static <T> T getService(DubboDefinition definition) {
        DubboService<?> dubboService = DUBBO_SERVICE_CONCURRENT_MAP.computeIfAbsent(definition.getKey(), key -> {
            return referenceDubbo(DUBBO_REFERENCE_ID_PREFIX + key, definition);
        });
        return (T) dubboService.getService();
    }

    private static <T> DubboService<T> referenceDubbo(String id, DubboDefinition definition) {
        ReferenceConfig<T> config = new ReferenceConfig<>();
        config.setId(id);
        config.setInterface(definition.getInterfaceName());
        config.setGroup(definition.getGroupName());
        config.setCheck(false);
        config.setVersion(definition.getVersion());
        return new DubboService<>(config);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DubboDefinition {
        private String interfaceName;
        private String groupName;
        private String version;

        public String getKey() {
            String key = interfaceName;
            if (groupName != null) {
                key = groupName + "/" + key;
            }

            if (version != null) {
                key += ":" + version;
            }
            return key;
        }
    }
}
