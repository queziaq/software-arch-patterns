package org.outbox.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.outbox.entity.OutboxEvent;
import org.outbox.repository.OutboxRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OutboxService {
    private OutboxRepository repository;
    private ObjectMapper objectMapper;

    public void saveEvent(Object event, UUID aggregateId, String aggregateType) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            OutboxEvent outboxEvent = OutboxEvent.builder()
                    .id(UUID.randomUUID())
                    .aggregateId(aggregateId)
                    .aggregateType(aggregateType)
                    .eventType(event.getClass().getSimpleName())
                    .payload(payload)
                    .build();

            repository.save(outboxEvent);
        } catch (Exception e) {
            throw new RuntimeException("Persistence Error", e);
        }
    }
}