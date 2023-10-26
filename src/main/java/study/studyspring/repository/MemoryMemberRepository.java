package study.studyspring.repository;

import org.springframework.stereotype.Repository;
import study.studyspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();   // 키 : id, 값 : Member
    private static long sequence = 0L;   // 키값을 생성해줌

    @Override
    public Member save(Member member) {    // 저장을 어딘가 해야한다
        member.setId(++sequence);   // store에 넣기 전에 멤버 id 값을 세팅해줌
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));   // null이 되어도 감쌀 수 있다.
    }

    @Override
    public Optional<Member> findyName(String name) {
        return store.values().stream()   // 람다형식
                .filter(member -> member.getName().equals(name))  // member의 name과 파라미터로 넘어온 name이 일치하는지 루프 돌리는 형태
                .findAny();   // 찾으면 반환됨 -> 결과는 Optional의 형태로 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());    // store에 있는 values는 위의 HashMap의 멤버들을 반환한다.
    }

    public void clearStore(){
        store.clear();
    }
}
