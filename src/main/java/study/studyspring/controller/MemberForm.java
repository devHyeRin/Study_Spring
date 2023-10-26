package study.studyspring.controller;

public class MemberForm {
    private String name;    // 이 name의 변수가 html의 input태그의 name에 설정한 값이랑 매치되면서 서버로 넘어간다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
