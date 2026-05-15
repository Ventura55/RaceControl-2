package org.control.racecontrol.infrastructure.persistence;

import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class KafkaMessageRelay {

    private final KafkaEventRepository eventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessageRelay(KafkaEventRepository eventRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.eventRepository = eventRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processMessages() {
        List<KafkaEventEntity> events = eventRepository.findByStatus("PENDING");

        for (KafkaEventEntity event : events) {
            try {
                kafkaTemplate.send(event.getTopic(), event.getPayload());

                event.setStatus("PROCESSED");
                event.setProcessedAt(LocalDateTime.now());
                eventRepository.save(event);

                System.out.println("Mensaje enviado y procesado");
            } catch (Exception e) {
                System.err.println("Error al enviarlo " + e.getMessage());
                event.setStatus("FAILED");
                eventRepository.save(event);
            }
        }
    }
}
