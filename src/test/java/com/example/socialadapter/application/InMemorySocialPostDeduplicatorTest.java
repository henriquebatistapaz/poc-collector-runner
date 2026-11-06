package com.example.socialadapter.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.socialadapter.domain.NormalizedSocialPost;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class InMemorySocialPostDeduplicatorTest {
    private final InMemorySocialPostDeduplicator deduplicator = new InMemorySocialPostDeduplicator();

    @Test
    void keepsFirstOccurrenceAndPreservesInputOrder() {
        NormalizedSocialPost first = post("alpha", "post-1", "first");
        NormalizedSocialPost duplicate = post("alpha", "post-1", "duplicate");
        NormalizedSocialPost second = post("alpha", "post-2", "second");

        assertEquals(List.of(first, second), deduplicator.acceptNew(List.of(first, duplicate, second)));
    }

    @Test
    void identityIncludesSourceAndPersistsAcrossCallsInMemory() {
        NormalizedSocialPost alpha = post("alpha", "same-id", "alpha");
        NormalizedSocialPost beta = post("beta", "same-id", "beta");

        assertEquals(List.of(alpha), deduplicator.acceptNew(List.of(alpha)));
        assertEquals(List.of(beta), deduplicator.acceptNew(List.of(alpha, beta)));
    }

    @Test
    void invalidBatchDoesNotPartiallyRecordEarlierPosts() {
        NormalizedSocialPost valid = post("alpha", "post-1", "valid");
        NormalizedSocialPost missingId = post("alpha", " ", "invalid");

        assertThrows(IllegalArgumentException.class,
                () -> deduplicator.acceptNew(List.of(valid, missingId)));
        assertEquals(List.of(valid), deduplicator.acceptNew(List.of(valid)));
    }

    private NormalizedSocialPost post(String source, String id, String text) {
        return new NormalizedSocialPost(source, id, "author", text,
                Instant.parse("2026-01-01T00:00:00Z"), "https://example.test/" + id);
    }
}
