package com.selfSpring.UserManagerment;

import com.selfSpring.UserManagerment.repository.JpaUserRepository;
import com.selfSpring.UserManagerment.repository.UserRepository;
import com.selfSpring.UserManagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;
    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(EntityManager em, UserRepository userRepository) {
        this.em = em;
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

    @Bean
    public UserRepository userRepository(EntityManager em) {
        return new JpaUserRepository(em);
    }
}
