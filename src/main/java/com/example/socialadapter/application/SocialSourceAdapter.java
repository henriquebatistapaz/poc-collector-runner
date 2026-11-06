package com.example.socialadapter.application;

import com.example.socialadapter.domain.NormalizedSocialPost;
import java.util.List;

/** TODO: define fixture input and error contract before implementing source adapters. */
public interface SocialSourceAdapter {
    String sourceName();
    List<NormalizedSocialPost> parseFixture(String fixture);
}
