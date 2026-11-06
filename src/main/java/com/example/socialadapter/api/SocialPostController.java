package com.example.socialadapter.api;

import com.example.socialadapter.application.InMemorySocialPostDeduplicator;
import com.example.socialadapter.domain.NormalizedSocialPost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Social posts", description = "Synthetic normalized social post ingestion")
public class SocialPostController {
    private final InMemorySocialPostDeduplicator deduplicator;

    public SocialPostController(InMemorySocialPostDeduplicator deduplicator) {
        this.deduplicator = deduplicator;
    }

    @PostMapping
    @Operation(summary = "Accept a normalized post unless its source and external ID were seen")
    public ResponseEntity<PostIngestionResponse> ingest(@RequestBody NormalizedSocialPost post) {
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post body is required");
        }
        try {
            List<NormalizedSocialPost> accepted = deduplicator.acceptNew(List.of(post));
            String status = accepted.isEmpty() ? "duplicate" : "accepted";
            HttpStatus httpStatus = accepted.isEmpty() ? HttpStatus.OK : HttpStatus.CREATED;
            return ResponseEntity.status(httpStatus)
                    .body(new PostIngestionResponse(post.source(), post.externalId(), status));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    public record PostIngestionResponse(String source, String externalId, String status) {}
}
