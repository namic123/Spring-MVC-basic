package servlet.servletmvc.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
    }
}
