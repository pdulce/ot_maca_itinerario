package giss.mad.itinerario.service;

import es.giss.arch.kafka.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService extends ConsumerService<String, Object> {

  public KafkaConsumerService() {
    super((k, v) -> log.info("######################" + String.valueOf(v)));
  }
}
