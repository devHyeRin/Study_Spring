package study.studyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    /*mvc 강의*/
    //외부에서 파라미터를 받게 한다. -> 주소창 입력을 통해 파라미터 전달 : localhost:8080/hello-mvc?name=spring
    // @RequestParam 어노테이션을 사용하는데 주소창 입력을 그냥 localhost:8080/hello-mvc만 할 경우 오류페이지 나타남 -> 파라미터 전달을 안했기 때문!
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);  // 파라미터로 넘어온 name
        return "hello-template";
    }

    /*api 강의*/
    // 하지만 이 방법을 쓸 일은 거의 없다
    @GetMapping("hello-string")
    @ResponseBody // 제일 중요한 어노테이션!
    public String helloString(@RequestParam("name") String name){   // 템플릿 엔진과 다르게 name에 적은 문자가 그대로 내려가게 됨! -> view 이런게 없음
        return "hello " + name;
    }

    // 이 코드가 진짜 중요함 -> 문자 이런게 아니고 데이터를 전달해라 라고 하면 api 방식을 많이 사용한다.
    // api 방식 : 객체를 반환하고 @ResponseBody를 해놓으면 JSON으로 반환하게 된다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;  // 객체를 리턴 -> api 방식
    }

    static class Hello{
        private String name;  // private이므로 외부에서 쓰지 못하므로 getter, setter 메소드를 통해서 접근할 수 있도록
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
