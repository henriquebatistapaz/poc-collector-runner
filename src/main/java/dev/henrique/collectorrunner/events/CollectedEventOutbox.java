package dev.henrique.collectorrunner.events;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "collected_event_outbox")
public class CollectedEventOutbox {
    @Id
    private String id;
    @Column(nullable = false, length = 610)
    private String eventKey;
    @Column(nullable = false, columnDefinition = "text")
    private String envelope;
    @Column(nullable = false)
    private Instant createdAt;

    protected CollectedEventOutbox() { }

    CollectedEventOutbox(String id, String eventKey, String envelope, Instant createdAt) {
        this.id = id;
        this.eventKey = eventKey;
        this.envelope = envelope;
        this.createdAt = createdAt;
    }

    String id() { return id; }
    String eventKey() { return eventKey; }
    String envelope() { return envelope; }
}
