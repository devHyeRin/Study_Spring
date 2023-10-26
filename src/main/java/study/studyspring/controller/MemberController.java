package study.studyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.studyspring.domain.Member;
import study.studyspring.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // memberController가 생성될 때 스프링 빈에 등록되어 있는 memberService의 객체를 넣어주게 된다. -> 의존관계를 주입한다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);    // 회원가입

        return "redirect:/";
    }
}
