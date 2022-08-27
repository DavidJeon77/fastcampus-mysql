package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import com.example.fastcampusmysql.factory.MemberFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GetFollowingMembersUsacaseTest {
    @Autowired
    private GetFollowingMembersUsacase usacase;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FollowWriteService followWriteService;

    @DisplayName("팔로잉 회원 목록 조회")
    @Test
    public void testExecute() {
        var member = saveMember();
        followWriteService.create(member, saveMember());

        var result = usacase.execute(member.getId());
        System.out.println(result);

    }

    private Member saveMember() {
        var member = MemberFixtureFactory.create();
        return memberRepository.save(member);
    }
}