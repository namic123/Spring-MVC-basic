package servlet.servletmvc.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 테스트가 끝나고 실행
    void afterEach(){   // 테스트 끝나면 초기화
        memberRepository.clearStore();
    }

    @Test
    void save(){    // 해당 멤버의 존재여부 확인 후 저장
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){     // 모든 멤버를 테스트
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2); // 저장된 멤버의 총 개수 test
        assertThat(result).contains(member1, member2); // 해당 멤버 포함 여부 테스트
    }
}
