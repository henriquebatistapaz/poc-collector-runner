package dev.henrique.collectorrunner.events;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CollectedEventRepository extends JpaRepository<CollectedEventOutbox, String> {
    List<CollectedEventOutbox> findTop50ByOrderByCreatedAtAscIdAsc();
}
