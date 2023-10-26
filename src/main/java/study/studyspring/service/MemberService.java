package study.studyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.studyspring.domain.Member;
import study.studyspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);  // 중복 회원 검증 (메소드로 만들어서 하는 것이 더 좋음!)

        /* Optional을 바로 반환하는 이 코드는 그닥 권장하지 않음 (위의 코드로 개선)
        Optional<Member> result = memberRepository.findyName(member.getName());

        result.ifPresent(m -> {   // result가 여기 만약 값이 있으면(멤버에 값이 있으면) -> Optional이기 때문에 사용할 수 있는 메소드이다.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findyName(member.getName())   // Optional을 바로 반환하는 이 코드는 그닥 권장하지 않음
            .ifPresent(m -> {   // result가 여기 만약 값이 있으면(멤버에 값이 있으면) -> Optional이기 때문에 사용할 수 있는 메소드이다.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
