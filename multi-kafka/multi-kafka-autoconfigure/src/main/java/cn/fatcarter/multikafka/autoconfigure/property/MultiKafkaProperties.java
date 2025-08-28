package cn.fatcarter.multikafka.autoconfigure.property;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.kafka.multi")
public class MultiKafkaProperties {
    @NestedConfigurationProperty
    private Map<String, KafkaProperties> clusters = new LinkedHashMap<>();

    public Map<String, KafkaProperties> getClusters() {
        return clusters;
    }

    public void setClusters(Map<String, KafkaProperties> clusters) {
        this.clusters = clusters;
    }
}
