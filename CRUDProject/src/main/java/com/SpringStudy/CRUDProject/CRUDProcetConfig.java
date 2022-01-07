package com.SpringStudy.CRUDProject;

import com.SpringStudy.CRUDProject.repository.JpaPostRepository;
import com.SpringStudy.CRUDProject.repository.PostRepository;
import com.SpringStudy.CRUDProject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class CRUDProcetConfig {

    private final EntityManager em;
    private final PostRepository postRepository;

    @Autowired
    public CRUDProcetConfig(EntityManager em, PostRepository postRepository) {
        this.em = em;
        this.postRepository = postRepository;
    }

    @Bean
    public PostRepository postRepository(EntityManager em) {
        return new JpaPostRepository(em);
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository);
    }
}
