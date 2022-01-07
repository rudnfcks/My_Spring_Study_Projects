package com.SpringStudy.CRUDProject.service;

import com.SpringStudy.CRUDProject.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired PostService postService;

    @Test
    void 게시물_작성() {
        Post post = new Post();

        post.setTitle("테스트 게시물 Test");
        post.setContent("테스트 게시물 Test 내용");
        post.setWriter("tester Test");

        postService.upload(post);
    }

    @Test
    void 게시물_전체찾기() {
        assertThat(postService.findPosts().size()).isEqualTo(2);
    }

    @Test
    void 게시물_제목으로찾기() {
        assertThat(postService.findPosts("1").size()).isEqualTo(1);
        assertThat(postService.findPosts("테스트").size()).isEqualTo(2);
    }

    @Test
    void 게시물_날짜로찾기() {
        assertThat(postService.findPosts(20220107, 20220108).size()).isEqualTo(2);
    }

    @Test
    void 게시물_제목_날짜로찾기() {
        assertThat(postService.findPosts("테스트", 20220107, 20220108).size()).isEqualTo(2);
        assertThat(postService.findPosts("1", 20220107, 20220108).size()).isEqualTo(1);
    }

    @Test
    void 게시물_하나찾기() {
        assertThat(postService.findOne(1L).get().getTitle()).isEqualTo("테스트 게시물1");
    }

    @Test
    void 게시물_삭제() {
        postService.delPost(1L);
        assertThat(postService.findPosts().size()).isEqualTo(1);
    }

    @Test
    void 게시물_수정() {
        postService.modifyPost(1L, "테스트 게시물Test", "테스트 게시물Text 내용");
        assertThat(postService.findPosts("Test").size()).isEqualTo(1);
    }
}