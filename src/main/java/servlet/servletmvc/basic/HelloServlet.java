package servlet.servletmvc.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// name: 서블릿 이름
// urlPattern: URL 매핑
// 서블릿명과 url매핑의 이름은 겹치면 안된다.
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
// 서블릿을 만드려면 HttpServlet class를 상속 받아야 한다.
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        /* 사용자 요청이 들어오면 서블릿이 요청 객체를 만들어줌 */
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        
        // URL 쿼리스트링을 가져오는 메서드
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 메시지 생성
        /* 헤더에 포함되는 정보 */
        response.setContentType("text/plain");  // 컨텐츠의 타입 명시
        response.setCharacterEncoding("utf-8"); // 인코딩 정보(구시스템이 아니면 utf-8을 사용)
        /* 나머지 컨텐츠의 정보는 톰캣서버에서 만들어줌. */
        /* 바디에 포함되는 정보 */
        response.getWriter().write("hello "+username); // 페이지에 텍스트 출력

        // 위와 같이 요청과 응답을 서블릿에서 만들어준다.

        // 서블릿 정리
        // 1. HTTP 요청
        // 2. WAS에서 HTTP 요청메시지를 기반으로 HttpServletRequest 객체 생성
        // 3. 요청에 해당하는 urlPatterns 매핑을 하여 서블릿 객체 실행(HttpServletResponse 작성 등)
        // 4. response 메세지 사용자에게 응답
    }
}
