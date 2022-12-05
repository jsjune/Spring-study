package com.example.sns.domain.post.repository;

import com.example.sns.domain.post.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom {

    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    List<Post> findAllByIdIn(List<Long> ids);
}
