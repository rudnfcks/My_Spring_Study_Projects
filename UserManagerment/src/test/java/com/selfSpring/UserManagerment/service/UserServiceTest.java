package com.selfSpring.UserManagerment.service;

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
class UserServiceTest {

    @Autowired UserService userService;

    @Test
    void 회원가입() {
        User user = new User();
        user.setName("spring");

        userService.join(user);
    }

    @Test
    void 중복회원가입() {
        try {
            User user1 = new User();
            user1.setName("spring1");

            User user2 = new User();
            user2.setName("spring1");

            userService.join(user1);
            userService.join(user2);
        }
        catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    }

    @Test
    void 전체유저() {
        User user1 = new User();
        user1.setName("spring1");

        User user2 = new User();
        user2.setName("spring2");

        userService.join(user1);
        userService.join(user2);

        List<User> result = userService.findUsers();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void 선택유저() {
        User user = new User();
        user.setName("spring1");

        Long index = userService.join(user);

        User result = userService.findOne(index).get();

        Assertions.assertThat(result.getId()).isEqualTo(index);
    }
}