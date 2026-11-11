package com.example.collectorrunner.application;

import com.example.collectorrunner.domain.NormalizedSocialPost;
import com.example.collectorrunner.persistence.SocialPostEntity;
import com.example.collectorrunner.persistence.SocialPostRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@lombok.RequiredArgsConstructor
public class SocialPostPersistenceService {
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;

    private final SocialPostRepository repository;


    @Transactional
    public List<NormalizedSocialPost> acceptNew(List<NormalizedSocialPost> posts) {
        if (posts == null) throw new IllegalArgumentException("Posts are required");
        for (NormalizedSocialPost post : posts) validate(post);

        List<NormalizedSocialPost> accepted = new ArrayList<>();
        for (NormalizedSocialPost post : posts) {
            String source = post.source().trim();
            String externalId = post.externalId().trim();
            if (!repository.existsBySourceAndExternalId(source, externalId)) {
                accepted.add(repository.save(new SocialPostEntity(post)).toDomain());
            }
        }
        return List.copyOf(accepted);
    }

    @Transactional(readOnly = true)
    public List<NormalizedSocialPost> findAll() {
        return repository.findAllByOrderByPublishedAtDesc().stream()
                .map(SocialPostEntity::toDomain).toList();
    }

    @Transactional(readOnly = true)
    public List<NormalizedSocialPost> findPage(String source, int page, int size) {
        if (page < 0) throw new IllegalArgumentException("page must be zero or greater");
        if (size < 1 || size > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("size must be between 1 and " + MAX_PAGE_SIZE);
        }
        PageRequest request = PageRequest.of(page, size);
        String filter = source == null || source.isBlank() ? null : source.trim();
        List<SocialPostEntity> entities = filter == null
                ? repository.findAllByOrderByPublishedAtDesc(request)
                : repository.findAllBySourceOrderByPublishedAtDesc(filter, request);
        return entities.stream().map(SocialPostEntity::toDomain).toList();
    }

    private void validate(NormalizedSocialPost post) {
        if (post == null) throw new IllegalArgumentException("Post is required");
        if (post.source() == null || post.source().isBlank()) {
            throw new IllegalArgumentException("Post source is required for deduplication");
        }
        if (post.source().trim().length() > 100) throw new IllegalArgumentException("Post source exceeds 100 characters");
        if (post.externalId() == null || post.externalId().isBlank()) {
            throw new IllegalArgumentException("Post externalId is required for deduplication");
        }
        if (post.externalId().trim().length() > 500) {
            throw new IllegalArgumentException("Post externalId exceeds 500 characters");
        }
    }
}
