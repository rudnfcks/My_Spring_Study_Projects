package com.selfSpring.UserManagerment.service;

import com.selfSpring.UserManagerment.domain.User;
import com.selfSpring.UserManagerment.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);

        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        userRepository.getByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<User> findUsers() {
        return userRepository.getAll();
    }

    public Optional<User> findOne(Long id) {
        return userRepository.getById(id);
    }
}
