package com.selfSpring.UserManagerment.repository;

import com.selfSpring.UserManagerment.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        List<User> result = em.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<User> getByName(String name) {
        List<User> result = em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
