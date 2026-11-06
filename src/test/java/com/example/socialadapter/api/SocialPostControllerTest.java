package com.example.socialadapter.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SocialPostControllerTest {
    @Autowired MockMvc mockMvc;

    @Test
    void acceptsFirstPostAndMarksRepeatAsDuplicate() throws Exception {
        String body = """
                {"source":"demo","externalId":"post-1","author":"sample","text":"hello",
                 "publishedAt":"2026-01-01T00:00:00Z","permalink":"https://example.test/post-1"}
                """;

        mockMvc.perform(post("/v1/posts").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("accepted"));
        mockMvc.perform(post("/v1/posts").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("duplicate"));
    }

    @Test
    void exposesOpenApiDocument() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.openapi").exists())
                .andExpect(jsonPath("$.paths['/v1/posts']").exists());
    }
}
