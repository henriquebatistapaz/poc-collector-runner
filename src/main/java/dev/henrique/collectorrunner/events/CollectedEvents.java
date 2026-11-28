package dev.henrique.collectorrunner.events;

import dev.henrique.collectorrunner.domain.NormalizedSocialPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.json.JsonMapper;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
@ConditionalOnProperty(name = "runner.events.enabled", havingValue = "true")
public class CollectedEvents {
    private final CollectedEventRepository outbox;
    private final KafkaTemplate<String, String> kafka;
    private final JsonMapper json;
    private final String topic;

    public CollectedEvents(CollectedEventRepository outbox, KafkaTemplate<String, String> kafka,
                           JsonMapper json, @Value("${runner.events.topic:collector.items}") String topic) {
        this.outbox = outbox;
        this.kafka = kafka;
        this.json = json;
        this.topic = topic;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void record(NormalizedSocialPost post) {
        String id = UUID.randomUUID().toString();
        String key = post.source().length() + ":" + post.source() + post.externalId();
        Instant now = Instant.now();
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("source", post.source());
        payload.put("externalId", post.externalId());
        payload.put("title", title(post.text()));
        payload.put("url", post.permalink());
        payload.put("collectedAt", now.toString());
        outbox.save(new CollectedEventOutbox(id, key, json.writeValueAsString(Map.of(
                "eventId", id, "eventType", "item.collected", "schemaVersion", "1",
                "source", "poc-collector-runner", "correlationId", key,
                "occurredAt", now.toString(), "payload", payload)), now));
    }

    private String title(String text) {
        if (text == null) return null;
        int length = Math.min(text.codePointCount(0, text.length()), 1000);
        return text.substring(0, text.offsetByCodePoints(0, length));
    }

    @Scheduled(fixedDelayString = "${runner.events.poll-ms:2000}")
    public void publishPending() throws Exception {
        for (var event : outbox.findTop50ByOrderByCreatedAtAscIdAsc()) {
            kafka.send(topic, event.eventKey(), event.envelope()).get(15, TimeUnit.SECONDS);
            outbox.deleteById(event.id());
        }
    }
}
