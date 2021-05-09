package com.oob.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {
    private String title;
    private String content;
    private String images;
    private LocalDateTime createdAt;
}
