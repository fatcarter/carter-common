package cn.fatcarter.multikafka.autoconfigure;

import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


/**
 * BeanName = {instanceName} + Kafka
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public class Kafka<K, V> {
    private final ApplicationContext applicationContext;
    private final String instanceName;
    private String kafkaTemplateName;
    private String consumerFactoryName;
    private String producerFactoryName;
    private String concurrentKafkaListenerContainerFactory;

    public Kafka(ApplicationContext applicationContext, String instanceName, String kafkaTemplateName, String consumerFactoryName, String producerFactoryName, String concurrentKafkaListenerContainerFactory) {
        this.applicationContext = applicationContext;
        this.instanceName = instanceName;
        this.kafkaTemplateName = kafkaTemplateName;
        this.consumerFactoryName = consumerFactoryName;
        this.producerFactoryName = producerFactoryName;
        this.concurrentKafkaListenerContainerFactory = concurrentKafkaListenerContainerFactory;
    }

    public Kafka(ApplicationContext applicationContext, String instanceName) {
        this.instanceName = instanceName;
        this.applicationContext = applicationContext;
    }

    public KafkaTemplate<K, V> getKafkaTemplate() {
        if (this.kafkaTemplateName == null) {
            this.kafkaTemplateName = this.instanceName + "KafkaTemplate";
        }
        return this.applicationContext.getBean(kafkaTemplateName, KafkaTemplate.class);
    }

    public ConsumerFactory<K, V> getConsumerFactory() {
        if (this.consumerFactoryName == null) {
            this.consumerFactoryName = this.instanceName + "ConsumerFactory";
        }
        return this.applicationContext.getBean(consumerFactoryName, ConsumerFactory.class);
    }

    public ProducerFactory<K, V> getProducerFactory() {
        if (this.producerFactoryName == null) {
            this.producerFactoryName = this.instanceName + "ProducerFactory";
        }
        return this.applicationContext.getBean(producerFactoryName, ProducerFactory.class);
    }

    public ConcurrentKafkaListenerContainerFactory<K, V> getKafkaListenerContainerFactory() {
        if (this.concurrentKafkaListenerContainerFactory == null) {
            this.concurrentKafkaListenerContainerFactory = this.instanceName + "KafkaListenerContainerFactory";
        }
        return this.applicationContext.getBean(concurrentKafkaListenerContainerFactory, ConcurrentKafkaListenerContainerFactory.class);
    }

}
