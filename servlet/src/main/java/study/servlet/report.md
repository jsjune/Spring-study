<%@ page import="hello.servlet.domain.member.MemberRepository" %><br>
자바의 import 문과 같다.

<% ~~ %><br>
이 부분에는 자바 코드를 입력할 수 있다.

<%= ~~ %><br>
이 부분에는 자바 코드를 출력할 수 있다.

/WEB-INF <br>
이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는 것은 항상 컨트롤러를통해서 JSP를 호출하는 것이다.

상대경로 사용, [현재 URL이 속한 계층 경로 + /save]<br>
현재 계층 경로: /servlet-mvc/members/<br>
결과: /servlet-mvc/members/save