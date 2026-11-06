package com.example.socialadapter.application;

import com.example.socialadapter.domain.NormalizedSocialPost;
import java.util.List;

/** Converts one synthetic source fixture format to the canonical model. */
public interface SocialSourceAdapter {
    String sourceName();

    /** @throws IllegalArgumentException when the fixture is malformed or contains invalid records. */
    List<NormalizedSocialPost> parseFixture(String fixture);
}
