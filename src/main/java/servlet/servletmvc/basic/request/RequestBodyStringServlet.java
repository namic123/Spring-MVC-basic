package servlet.servletmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream(); // 메시지 바디를 바이트코드로 받을 수 있음.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 위 메시지바디의 바이트코드를 String형식으로 변환

        System.out.println("messageBody = " + messageBody);
        resp.getWriter().write("ok");
    }
}
