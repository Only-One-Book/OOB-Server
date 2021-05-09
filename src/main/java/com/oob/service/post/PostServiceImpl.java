package com.oob.service.post;

import com.oob.domain.entity.Post;
import com.oob.domain.entity.User;
import com.oob.domain.payload.request.PostRequest;
import com.oob.domain.payload.response.ApplicationListResponse;
import com.oob.domain.payload.response.PostListResponse;
import com.oob.domain.payload.response.PostResponse;
import com.oob.domain.repository.PostRepository;
import com.oob.domain.repository.UserRepository;
import com.oob.exception.PostNotFoundException;
import com.oob.exception.UserNotFoundException;
import com.oob.exception.UserNotSameException;
import com.oob.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthenticationFacade authenticationFacade;

    @SneakyThrows
    @Override
    public void write(PostRequest postRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.save(
                Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(user.getId())
                .createdAt(LocalDateTime.now())
                .build()
        );
        postRepository.save(post);
    }

    @Override
    public ApplicationListResponse postList(Pageable pageable) {
        return this.searchPostList(pageable);
    }

    @Override
    public PostResponse getPost(Integer postId) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        User author = userRepository.findById(post.getAuthor())
                .orElseThrow(UserNotFoundException::new);

        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Override
    public void modifyPost(PostRequest postRequest, Integer postId) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if(!user.getId().equals(post.getAuthor()))
            throw new UserNotSameException();

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        postRepository.save(post);
    }

    @Override
    public void deletePost(Integer postId) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if(!user.getId().equals(post.getAuthor()))
            throw new UserNotSameException();

        postRepository.deleteById(postId);
    }

    private ApplicationListResponse searchPostList(Pageable pageable) {
        Page<Post> postPage = postRepository.findAllBy(pageable);

        List<PostListResponse> postListResponses = new ArrayList<>();

        for(Post post : postPage) {
            User writer = userRepository.findById(post.getAuthor())
                    .orElseThrow(UserNotFoundException::new);

            postListResponses.add(
                    PostListResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .author(writer.getName())
                    .createdAt(post.getCreatedAt())
                    .build()

            );
        }

        return ApplicationListResponse.builder()
                .totalElements((int) postPage.getTotalElements())
                .totalPages(postPage.getTotalPages())
                .applicationResponses(postListResponses)
                .build();
    }
}
