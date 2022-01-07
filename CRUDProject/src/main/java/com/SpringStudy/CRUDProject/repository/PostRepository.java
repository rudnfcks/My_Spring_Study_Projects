package com.SpringStudy.CRUDProject.repository;

import com.SpringStudy.CRUDProject.domain.Post;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByDate(Date startDate, Date endDate);
    List<Post> findByTitleAndDate(String title, Date startDate, Date endDate);
    void delete(Long id);
    Long modify(Long id, String title, String content);
}
