package com.SpringStudy.UsePostgreSQL.repository;

import com.SpringStudy.UsePostgreSQL.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    void saveTest() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);
    }
}