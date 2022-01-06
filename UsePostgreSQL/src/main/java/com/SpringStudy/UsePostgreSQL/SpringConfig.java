package com.SpringStudy.UsePostgreSQL;

import com.SpringStudy.UsePostgreSQL.repository.JpaMemberRepository;
import com.SpringStudy.UsePostgreSQL.repository.MemberRepository;
import com.SpringStudy.UsePostgreSQL.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private MemberRepository memberRepository;
    private final EntityManager em;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, EntityManager em) {
        this.memberRepository = memberRepository;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public MemberRepository memberRepository(EntityManager em) {
        return new JpaMemberRepository(em);
    }
}
