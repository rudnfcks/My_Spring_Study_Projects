package com.SpringStudy.CRUDProject.repository;

import com.SpringStudy.CRUDProject.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaPostRepositoryTest {
    @Autowired PostRepository postRepository;

    @Test
    void 저장_테스트() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(now);

        Post post = new Post();

        post.setTitle("테스트 게시물 Test");
        post.setContent("테스트 게시물 내용");
        post.setDate(date);
        post.setChangeDate("");
        post.setWriter("tester");

        postRepository.save(post);
    }

    @Test
    void 전체_조회_테스트() {
        int result = postRepository.findAll().size();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void 아이디_검색_테스트() {
        Post result = postRepository.findById(1L).get();

        assertThat(result.getTitle()).isEqualTo("테스트 게시물1");
    }

    @Test
    void 제목_검색_테스트() {
        List<Post> result1 = postRepository.findByTitle("테스트");
        assertThat(result1.size()).isEqualTo(2);

        List<Post> result2 = postRepository.findByTitle("1");
        assertThat(result2.size()).isEqualTo(1);

        List<Post> result3 = postRepository.findByTitle("2");
        assertThat(result3.size()).isEqualTo(1);
    }

    @Test
    void 날짜_검색_테스트() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start = dateFormat.parse("2022-01-07 10:19:11");
        Date end = dateFormat.parse("2022-01-07 10:19:13");

        List<Post> result1 = postRepository.findByDate(start, end);
        assertThat(result1.size()).isEqualTo(1);

        start = dateFormat.parse("2022-01-07 10:19:11");
        end = dateFormat.parse("2022-01-07 10:21:13");

        List<Post> result2 = postRepository.findByDate(start, end);
        assertThat(result2.size()).isEqualTo(2);
    }

    @Test
    void 삭제_테스트() {
        postRepository.delete(1L);

        assertThat(postRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void 수정_테스트() {
        postRepository.modify(1L, "변경 테스트 게시물1","변경 테스트 게시물 내용");

        assertThat(postRepository.findByTitle("변경").size()).isEqualTo(1);
    }
}