package study.studyspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.studyspring.domain.Member;
import study.studyspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach   // 동작하기 전 실행 -> 테스트를 실행할 때마다 각각 생성을 해준다.
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();    // 이전의 테스트 db값을 클리어해주는 역할
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);   // 리턴 타입은 저장한 아이디가 나와야 함

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());  // 이름 검증
    }

    /**
     * 회원가입에서 발생하는 예외 플로우 테스트 코드 작성
     */
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));   // IllegalStateException을 반환해서 assertThat에서 비교할 때 사용

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");     // memberService의 join() 메소드의 중복회원검증할 때 설정한 예외 메시지와 같은지 확인함
 /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}