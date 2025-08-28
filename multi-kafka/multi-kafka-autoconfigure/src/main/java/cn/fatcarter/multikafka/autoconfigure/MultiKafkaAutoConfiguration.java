package cn.fatcarter.multikafka.autoconfigure;

import cn.fatcarter.multikafka.autoconfigure.property.MultiKafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(MultiKafkaProperties.class)
public class MultiKafkaAutoConfiguration {


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Configuration
    static class Registrar implements BeanPostProcessor {
        private static final Logger log = LoggerFactory.getLogger(Registrar.class);

        private final GenericApplicationContext context;
        private final MultiKafkaProperties props;
        private final ObjectProvider<RecordMessageConverter> recordMessageConverter;
        private boolean registered = false;

        Registrar(GenericApplicationContext context,
                  MultiKafkaProperties props,
                  ObjectProvider<RecordMessageConverter> recordMessageConverter) {
            this.context = context;
            this.props = props;
            this.recordMessageConverter = recordMessageConverter;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (registered) {
                return bean;
            }
            this.registered = true;
            if (props.getClusters() == null || props.getClusters().isEmpty()) {
                log.warn("kafka clusters is empty ");
                return bean;
            }
            // 按集群名逐一注册
            for (Map.Entry<String, KafkaProperties> e : props.getClusters().entrySet()) {
                String name = e.getKey();
                KafkaProperties kp = e.getValue();

                // 1) 使用 KafkaProperties 的构建方法，完整支持全部参数
                Map<String, Object> consumerProps = kp.buildConsumerProperties();
                Map<String, Object> producerProps = kp.buildProducerProperties();

                // 推荐：如果未设置 group.id，而你又要使用 @KafkaListener，给一个兜底
                consumerProps.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, "group-" + name);

                // 2) 工厂
                ConsumerFactory<Object, Object> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
                ProducerFactory<Object, Object> pf = new DefaultKafkaProducerFactory<>(producerProps);
                KafkaTemplate<Object, Object> kt = new KafkaTemplate<>(pf);

                // 3) 监听容器工厂 —— Bean 名直接用集群名，方便 @KafkaListener(containerFactory="clusterA")
                ConcurrentKafkaListenerContainerFactory<Object, Object> lcf =
                    new ConcurrentKafkaListenerContainerFactory<>();
                lcf.setConsumerFactory(cf);

                // 3.1 可选：应用 Listener 层配置（并非强制，复用 KafkaProperties 的 listener 配置）
                if (kp.getListener() != null) {
                    KafkaProperties.Listener listener = kp.getListener();

                    if (listener.getConcurrency() != null) {
                        lcf.setConcurrency(listener.getConcurrency());
                    }
                    if (listener.getAckMode() != null) {
                        lcf.getContainerProperties().setAckMode(listener.getAckMode());
                    }
                    if (listener.getType() != null) {
                        lcf.setBatchListener(listener.getType() == KafkaProperties.Listener.Type.BATCH);
                    }
                    if (listener.getPollTimeout() != null) {
                        lcf.getContainerProperties().setPollTimeout(listener.getPollTimeout().toMillis());
                    }
                    if (listener.getClientId() != null) {
                        lcf.getContainerProperties().setClientId(listener.getClientId());
                    }
                    if (listener.getIdleEventInterval() != null) {
                        lcf.getContainerProperties().setIdleEventInterval(listener.getIdleEventInterval().toMillis());
                    }
                    if (listener.getAckCount() != null) {
                        lcf.getContainerProperties().setAckCount(listener.getAckCount());
                    }
                    if (listener.getAckTime() != null) {
                        lcf.getContainerProperties().setAckTime(listener.getAckTime().toMillis());
                    }
                }

                // 3.2 可选：应用全局/外部定义的 RecordMessageConverter（比如 JSON 转换）
                RecordMessageConverter converter = recordMessageConverter.getIfAvailable();
                if (converter != null) {
                    lcf.setRecordMessageConverter(converter);
                }

                // 4) 动态注册 Bean
                String cfBeanName = name + "ConsumerFactory";
                context.registerBean(cfBeanName, ConsumerFactory.class, () -> cf);
                log.info("Registered Kafka ConsumerFactory bean {}", cfBeanName);

                String pfBeanName = name + "ProducerFactory";
                context.registerBean(pfBeanName, ProducerFactory.class, () -> pf);
                log.info("Registered Kafka ProducerFactory bean {}", pfBeanName);

                String ktBeanName = name + "KafkaTemplate";
                context.registerBean(ktBeanName, KafkaTemplate.class, () -> kt);
                log.info("Registered Kafka KafkaTemplate bean {}", ktBeanName);

                String lcfAlias = name + "KafkaListenerContainerFactory";
                context.registerBean(name, ConcurrentKafkaListenerContainerFactory.class, () -> lcf);
                // 也可顺便加个别名（非必需）
                context.registerAlias(name, name + "ListenerContainerFactory");
                log.info("Kafka Cluster [{}] registered! ContainerFactory bean {} with alias {}", name, name, lcfAlias);
            }
            return bean;
        }
    }

}
