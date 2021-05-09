package com.oob.service.post;

import com.oob.domain.payload.request.PostRequest;
import com.oob.domain.payload.response.ApplicationListResponse;
import com.oob.domain.payload.response.PostResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {
    void write(PostRequest postRequest);
    ApplicationListResponse postList(Pageable pageable);
    PostResponse getPost(Integer postId);
    void modifyPost(PostRequest postRequest, Integer postId);
    void deletePost(Integer postId);
}
