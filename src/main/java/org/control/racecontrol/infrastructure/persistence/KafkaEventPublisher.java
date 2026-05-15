package org.control.racecontrol.infrastructure.persistence;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.port.output.RaceEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Primary
public class KafkaEventPublisher implements RaceEventPublisher {

    private final KafkaEventRepository eventRepository;
    private final ObjectMapper objectMapper;

    public KafkaEventPublisher(KafkaEventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishPenaltyAppliedEvent(Penalty penalty) {
        try {
            KafkaEventEntity event = new KafkaEventEntity();
            event.setTopic("fia.race.events");
            event.setStatus("PENDING");
            String payload = objectMapper.writeValueAsString(penalty);
            event.setPayload(payload);
            eventRepository.save(event);

            System.out.println("Evento guardado correctamente");
        } catch (Exception e) {
            throw new RuntimeException("Error evento ", e);
        }
    }
}
