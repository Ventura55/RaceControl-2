package org.control.racecontrol.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KafkaEventRepository extends JpaRepository<KafkaEventEntity, Long> {
    List<KafkaEventEntity> findByStatus(String status);
}
