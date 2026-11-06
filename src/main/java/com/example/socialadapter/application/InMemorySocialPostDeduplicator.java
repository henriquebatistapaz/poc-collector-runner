package com.example.socialadapter.application;

import com.example.socialadapter.domain.NormalizedSocialPost;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/** Keeps the first normalized post for each source and external ID during this process lifetime. */
@Service
public class InMemorySocialPostDeduplicator {
    private final Set<PostIdentity> seen = new HashSet<>();

    public synchronized List<NormalizedSocialPost> acceptNew(List<NormalizedSocialPost> posts) {
        if (posts == null) throw new IllegalArgumentException("Posts are required");

        Set<PostIdentity> pending = new HashSet<>();
        List<NormalizedSocialPost> accepted = new ArrayList<>();
        for (NormalizedSocialPost post : posts) {
            if (post == null) throw new IllegalArgumentException("Post is required");
            PostIdentity identity = identityOf(post);
            if (!seen.contains(identity) && pending.add(identity)) accepted.add(post);
        }

        // Only publish identities after the whole batch has passed validation.
        seen.addAll(pending);
        return List.copyOf(accepted);
    }

    private PostIdentity identityOf(NormalizedSocialPost post) {
        if (post.source() == null || post.source().isBlank()) {
            throw new IllegalArgumentException("Post source is required for deduplication");
        }
        if (post.externalId() == null || post.externalId().isBlank()) {
            throw new IllegalArgumentException("Post externalId is required for deduplication");
        }
        return new PostIdentity(post.source(), post.externalId());
    }

    private record PostIdentity(String source, String externalId) {}
}
