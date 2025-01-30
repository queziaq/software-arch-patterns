package org.outbox.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.outbox.entity.OutboxEvent;
import org.outbox.repository.OutboxRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OutboxProcessor {
    private OutboxRepository repository;
    private EventPublisher eventPublisher;

    @Scheduled(fixedRate = 5000)
    public void processOutboxEvents() {
        List<OutboxEvent> events = repository.findAll();

        for (OutboxEvent event : events) {
            try {
                eventPublisher.publish(event);
                repository.delete(event);
            } catch (Exception e) {
                System.out.println("error publishing event" + event.getId() +  e);
            }
        }
    }
}