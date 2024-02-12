package servlet.servletmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
* 1. 파라미터 전송 기능
* http://localhost:8080/request-param?username=park&age=29
* */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회 - start]");
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName +"=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회 - end]");

        System.out.println("-------------------------------------------------------------");

        System.out.println("[단일 파라미터 조회 - start]");
        String username = request.getParameter("username"); // 하나의 파라미터 이름에서 하나의 값을 읽을 때 사용
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회 - end]");

        System.out.println("-------------------------------------------------------------");

        System.out.println("[이름이 같은 복수 파라미터 조회 - start]");
        // 중복으로 사용하는 경우는 많지 않음
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames){
            System.out.println("name = " + name);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회 - end]");
    }
}
