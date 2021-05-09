package com.oob.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostListResponse {
    private Integer id;

    private String title;

    private String author;

    private LocalDateTime createdAt;
}
