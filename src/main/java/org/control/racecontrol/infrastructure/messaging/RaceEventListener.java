package org.control.racecontrol.infrastructure.messaging;

import org.control.racecontrol.infrastructure.input.rest.dto.response.PenaltyEventPayload;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class RaceEventListener {
    @KafkaListener(topics = "fia.race.events", groupId = "tv-station-group")
    public void logInTv(String payload) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("[LIVE TV] ¡MENSAJE RECIBIDO!");
        System.out.println("CONTENIDO: " + payload);
        System.out.println("\n-----------------------------------------------------");
    }
}
