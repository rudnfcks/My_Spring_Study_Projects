package com.selfSpring.UserManagerment.repository;

import com.selfSpring.UserManagerment.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaUserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void save() {
        User user = new User();
        user.setName("spring1");

        userRepository.save(user);
    }

    @Test
    void getById() {
        User user = new User();
        user.setName("spring1");

        userRepository.save(user);

        User result  = userRepository.getById(1L).get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void getByName() {
        User user = new User();
        user.setName("spring1");

        userRepository.save(user);

        User result = userRepository.getByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void getAll() {
        User user1 = new User();
        user1.setName("spring1");

        User user2 = new User();
        user2.setName("spring2");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> result = userRepository.getAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}