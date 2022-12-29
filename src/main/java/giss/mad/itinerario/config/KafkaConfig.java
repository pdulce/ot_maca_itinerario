package giss.mad.itinerario.config;

import es.giss.arch.core.autoconfiguration.kafka.client.consumer.EnableGissKafkaConsumer;
import org.springframework.context.annotation.Configuration;

@EnableGissKafkaConsumer
@Configuration
public class KafkaConfig {
}
