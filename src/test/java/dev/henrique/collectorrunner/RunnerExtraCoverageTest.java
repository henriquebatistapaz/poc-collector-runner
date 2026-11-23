package dev.henrique.collectorrunner;

import dev.henrique.collectorrunner.api.SocialPostController;
import dev.henrique.collectorrunner.application.SocialPostPersistenceService;
import dev.henrique.collectorrunner.domain.NormalizedSocialPost;
import dev.henrique.collectorrunner.integration.CollectedItemInput;
import dev.henrique.collectorrunner.integration.CollectedItemWriter;
import dev.henrique.collectorrunner.persistence.SocialPostRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RunnerExtraCoverageTest {

    @Test
    void collectedItemWriterCoversAllBranches() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1, 0);

        CollectedItemWriter writer = new CollectedItemWriter(ds);
        assertThrows(IllegalArgumentException.class, () -> writer.write(null));
        assertThrows(IllegalArgumentException.class, () -> writer.write(Collections.nCopies(1001,
                new CollectedItemInput("s", "1", "t", "https://example.com", Instant.now()))));
        assertEquals(new CollectedItemWriter.WriteResult(0, 0), writer.write(List.of()));

        List<CollectedItemInput> batch = List.of(
                new CollectedItemInput("s", "1", "t1", "https://example.com/1", Instant.now()),
                new CollectedItemInput("s", "2", "t2", "https://example.com/2", null)
        );
        CollectedItemWriter.WriteResult res = writer.write(batch);
        assertEquals(1, res.inserted());
        assertEquals(1, res.duplicates());
        verify(conn).commit();

        // Rollback + suppressed rollback failure branch
        when(ps.executeUpdate()).thenThrow(new SQLException("insert failed"));
        doThrow(new SQLException("rollback failed")).when(conn).rollback();
        assertThrows(SQLException.class, () -> writer.write(batch));
    }

    @Test
    void persistenceAndControllerCoverValidationBranches() {
        SocialPostRepository repo = mock(SocialPostRepository.class);
        when(repo.search(any(), any(), any(), any(), any())).thenReturn(Page.empty());
        SocialPostPersistenceService svc = new SocialPostPersistenceService(repo);

        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(null));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(Collections.singletonList(null)));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(List.of(
                new NormalizedSocialPost(null, "1", "a", "c", Instant.now(), "https://e.com"))));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(List.of(
                new NormalizedSocialPost("  ", "1", "a", "c", Instant.now(), "https://e.com"))));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(List.of(
                new NormalizedSocialPost("x".repeat(101), "1", "a", "c", Instant.now(), "https://e.com"))));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(List.of(
                new NormalizedSocialPost("s", null, "a", "c", Instant.now(), "https://e.com"))));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(List.of(
                new NormalizedSocialPost("s", "  ", "a", "c", Instant.now(), "https://e.com"))));
        assertThrows(IllegalArgumentException.class, () -> svc.acceptNew(List.of(
                new NormalizedSocialPost("s", "x".repeat(501), "a", "c", Instant.now(), "https://e.com"))));

        // search branches
        assertThrows(IllegalArgumentException.class, () -> svc.search(null, null, null, null, -1, 10));
        assertThrows(IllegalArgumentException.class, () -> svc.search(null, null, null, null, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> svc.search(null, null, null, null, 0, 101));
        assertThrows(IllegalArgumentException.class, () -> svc.search(null, null, Instant.now().plusSeconds(10), Instant.now(), 0, 10));
        assertThrows(IllegalArgumentException.class, () -> svc.search("x".repeat(101), null, null, null, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> svc.search(null, "x".repeat(201), null, null, 0, 10));
        assertNotNull(svc.search("  ", "  ", Instant.now(), null, 0, 10));
        assertNotNull(svc.search("s", "q", null, Instant.now(), 0, 10));

        // delete and deleteCreatedBefore branches
        assertThrows(IllegalArgumentException.class, () -> svc.delete(null, "1"));
        assertThrows(IllegalArgumentException.class, () -> svc.delete("  ", "1"));
        assertThrows(IllegalArgumentException.class, () -> svc.delete("s", null));
        assertThrows(IllegalArgumentException.class, () -> svc.delete("s", "  "));
        assertThrows(IllegalArgumentException.class, () -> svc.deleteCreatedBefore(null));
        when(repo.deleteBySourceAndExternalId("s", "1")).thenReturn(0L);
        assertFalse(svc.delete("s", "1"));

        // controller branches
        SocialPostController ctrl = new SocialPostController(svc);
        assertThrows(ResponseStatusException.class, () -> ctrl.ingest(null));
        assertThrows(ResponseStatusException.class, () -> ctrl.delete("s", "1", "key")); // adminApiKey is null
        ReflectionTestUtils.setField(ctrl, "adminApiKey", "   ");
        assertThrows(ResponseStatusException.class, () -> ctrl.delete("s", "1", "key"));
        ReflectionTestUtils.setField(ctrl, "adminApiKey", "secret");
        assertThrows(ResponseStatusException.class, () -> ctrl.delete("s", "1", null));
        assertThrows(ResponseStatusException.class, () -> ctrl.delete("  ", "1", "secret"));
        assertThrows(ResponseStatusException.class, () -> ctrl.delete("s", "1", "secret")); // 404 not found
    }
}
