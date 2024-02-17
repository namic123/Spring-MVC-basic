package servlet.servletmvc.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servletmvc.domain.member.Member;
import servlet.servletmvc.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;

// 회원 정보 저장
@WebServlet(name="memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    // 싱글톤으로 객체를 가져오므로, new 연산자가 아니라 인스턴스를 호출함.
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));   // 파라미터는 늘 String으로 반환되므로 int형으로 변환

        // 요청 파라미터를 member DTO에 할당
        Member member = new Member(username, age);
        // 리포지토리에 멤버 정보 저장
        memberRepository.save(member);

        // HTTP 응답 쓰기
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");


    }
}
