package org.outbox.auto;

import lombok.AllArgsConstructor;
import org.outbox.service.OutboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AddThingsOnDatabaseAutomatically {
    private OutboxService outboxService;

    @Scheduled(fixedRate = 5000)
    public void execute() {
        outboxService.saveEvent("This is the payload of the event", UUID.randomUUID(), "AGGREGATE_" + new Random().ints());
    }
}
