package com.SpringStudy.CRUDProject.controller;

import com.SpringStudy.CRUDProject.domain.Post;
import com.SpringStudy.CRUDProject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> postList(
            @RequestParam(value = "title", required = false) String title ,
            @RequestParam(value = "sDate", required = false) Integer sDate,
            @RequestParam(value = "eDate", required = false) Integer eDate,
            @RequestParam(value = "sort", required = false) String sort) {

        List<Post> result = getPosts(title, sDate, eDate);

        if (sort != null) {
            if (sort.equals("latest")) {
                return result.stream()
                        .sorted(Comparator.comparing(Post::getWriteDate))
                        .collect(Collectors.toList());
            } else if (sort.equals("old")) {
                return result.stream()
                        .sorted(Comparator.comparing(Post::getWriteDate).reversed())
                        .collect(Collectors.toList());
            } else {
                return null;
            }
        }
        return result;
    }

    public List<Post> getPosts(String title, Integer sDate, Integer eDate) {
        if (title != null && sDate != null && eDate != null) {
            if(sDate > eDate || sDate < 20220101 || eDate > 99999999) {
                return null;
            }
            return postService.findPosts(title, sDate, eDate);
        }
        else if (sDate != null && eDate != null) {
            if(sDate > eDate || sDate < 20220101 || eDate > 99999999) {
                return null;
            }
            return postService.findPosts(sDate, eDate);
        }
        else if (title != null) {
            return postService.findPosts(title);
        } else {
            return postService.findPosts();
        }
    }

    @GetMapping("/post")
    @ResponseBody
    public Post viewPost(@RequestParam("id") Long id) {
        return postService.findOne(id).get();
    }

    @PostMapping("/post")
    @ResponseBody
    public Post uploadPost(PostForm form) {
        Post post = new Post();
        if (form.getTitle() == null || form.getContent() == null || form.getWriter() == null) {
            return null;
        }

        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setWriter(form.getWriter());

        postService.upload(post);

        return post;
    }

    @PutMapping("/post")
    @ResponseBody
    public Post modifyPost(@RequestParam("id") Long id, PostForm form) {
        postService.modifyPost(id, form.getTitle(), form.getContent());

        return postService.findOne(id).get();
    }

    @DeleteMapping("/post")
    public void deletePost(@RequestParam("id") Long id) {
        postService.delPost(id);
    }
}
