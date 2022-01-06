package com.SpringStudy.UsePostgreSQL.service;

import com.SpringStudy.UsePostgreSQL.domain.Member;
import com.SpringStudy.UsePostgreSQL.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        checkDuplicateMember(member.getName());
        memberRepository.save(member);

        return member.getId();
    }

    public void checkDuplicateMember(String name) {
        memberRepository.findByName(name)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
