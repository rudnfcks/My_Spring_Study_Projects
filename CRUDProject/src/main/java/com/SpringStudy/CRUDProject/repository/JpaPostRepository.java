package com.SpringStudy.CRUDProject.repository;

import com.SpringStudy.CRUDProject.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaPostRepository implements PostRepository{

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> result = em.createQuery("select p from Post p where p.id = :id", Post.class)
                .setParameter("id", id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Post> findByTitle(String title) {
        List<Post> allList = findAll();

        List<Post> result = allList.stream()
                .filter(post -> post.getTitle().contains(title))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    // date = yyyy-mm-dd hh:MM:SS
    public List<Post> findByDate(Date startDate, Date endDate) {
        List<Post> allList = findAll();

        List<Post> result = allList.stream()
                .filter(post -> (startDate.before(post.getWriteDate()) && endDate.after(post.getWriteDate())))
                .collect(Collectors.toList());

        return result;
    }


    public List<Post> findByTitleAndDate(String title, Date startDate, Date endDate) {
        List<Post> titleList = findByTitle(title);

        List<Post> result = titleList.stream()
                .filter(post -> (startDate.before(post.getWriteDate()) && endDate.after(post.getWriteDate())))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void delete(Long id) {
        Post post = em.find(Post.class, id);

        em.remove(post);
    }

    @Override
    public Long modify(Long id, String title, String content) {
        Date now = new Date();

        Post post = em.find(Post.class, id);

        post.setTitle(title);
        post.setContent(content);
        post.setChangeDate(dateFormat.format(now));

        em.merge(post) ;

        return id;
    }
}
