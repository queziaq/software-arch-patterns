package org.outbox.event;

import lombok.AllArgsConstructor;
import org.outbox.entity.OutboxEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventPublisher {
    private ApplicationEventPublisher eventPublisher;
    public void publish(OutboxEvent event) {
        eventPublisher.publishEvent(event);
        System.out.println("event published" + event.getEventType() + " -> " + event.getPayload());
    }
}
