package study.studyspring.repository;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.studyspring.domain.Member;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach    // 콜백 메소드 (중요한 코드!)  -> MemoryMemberRepository에 clearStore 메소드 선언해야함!
    public void afterEach(){
        repository.clearStore();   // 테스트가 실행되고 끝날 때마다 한번씩 저장소를 비워준다. -> 순서와 상관이 없어짐!
    }

    @Test
    public void save() {
        // 멤버 저장 테스트
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();    // findById의 반환타입이 Optional이기 때문에 Optional에서 값을 꺼낼 때는 get()으로 꺼내기
//        Assertions.assertEquals(member, result);

        // 위의 코드보다 요즘에는 이 방법을 많이 사용
        assertThat(member).isEqualTo(result);

    }

    //이름으로 회원찾기 테스트
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findyName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    //전체 회원 조회
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}