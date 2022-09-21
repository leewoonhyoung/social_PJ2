package ORG.STDCD.BOARD.repository;

import ORG.STDCD.BOARD.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;


@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Member 넣기 test")
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .email("user"+i + "@aaa.com")
                    .password("1111")
                    .name("User" + i)
                    .build();

            memberRepository.save(member);
        });
    }
}