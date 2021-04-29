package com.oob.domain.repository;

import com.oob.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllBy(Pageable pageable);
    List<Post> findAllByOrderByCreatedAtAsc();
    Optional<Post> findById(Integer id);
    void deleteById(Integer id);
}
