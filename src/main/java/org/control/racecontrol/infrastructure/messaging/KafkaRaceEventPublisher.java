package org.control.racecontrol.infrastructure.messaging;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.port.output.RaceEventPublisher;
import org.control.racecontrol.infrastructure.input.rest.dto.response.PenaltyEventPayload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaRaceEventPublisher implements RaceEventPublisher {
    // Herramienta para hablar con kafka
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "fia.race.events";

    public KafkaRaceEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishPenaltyAppliedEvent(Penalty penalty) {
        PenaltyEventPayload payload = new PenaltyEventPayload(
                penalty.getIdRaceResult(),
                penalty.getPentaltySeconds(),
                penalty.getReason()
        );

        System.out.println("📢 [PRODUCTOR] Enviando mensaje a Kafka para el piloto " + payload.getDriverId() + "...");
        kafkaTemplate.send(TOPIC, payload);
        System.out.println("✅ [PRODUCTOR] Mensaje enviado a Kafka correctamente.");
    }
}
