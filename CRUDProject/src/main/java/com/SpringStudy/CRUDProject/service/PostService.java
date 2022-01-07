package com.SpringStudy.CRUDProject.service;

import com.SpringStudy.CRUDProject.domain.Post;
import com.SpringStudy.CRUDProject.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long upload(Post post) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        post.setWriteDate(dateFormat.format(now));
        postRepository.save(post);

        return post.getId();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Post> findPosts(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findPosts(Integer startDate, Integer endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start = new Date();
        Date end = new Date();

        try {
            start = dateFormat.parse(integerToDateString(startDate, true));
            end = dateFormat.parse(integerToDateString(endDate, false));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return postRepository.findByDate(start, end);
    }

    public List<Post> findPosts(String title, int startDate, Integer endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start = new Date();
        Date end = new Date();

        try {
            start = dateFormat.parse(integerToDateString(startDate, true));
            end = dateFormat.parse(integerToDateString(endDate, false));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return postRepository.findByTitleAndDate(title, start, end);
    }

    private String integerToDateString(Integer input, Boolean isStart) {
        String date = Integer.toString(input);

        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6,8);

        if (isStart) {
            return year + "-" + month + "-" + day + " 00:00:00";
        } else {
            return year + "-" + month + "-" + day + " 23:59:59";
        }
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }

    public void delPost(Long id) {
        postRepository.delete(id);
    }

    public Long modifyPost(Long id, String title, String content) {
        return postRepository.modify(id, title, content);
    }
}
