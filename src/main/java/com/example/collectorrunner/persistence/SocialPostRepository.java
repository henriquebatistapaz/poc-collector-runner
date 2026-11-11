package com.example.collectorrunner.persistence;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialPostRepository extends JpaRepository<SocialPostEntity, Long> {
    boolean existsBySourceAndExternalId(String source, String externalId);

    List<SocialPostEntity> findAllByOrderByPublishedAtDesc();

    List<SocialPostEntity> findAllByOrderByPublishedAtDesc(Pageable pageable);

    List<SocialPostEntity> findAllBySourceOrderByPublishedAtDesc(String source, Pageable pageable);
}
