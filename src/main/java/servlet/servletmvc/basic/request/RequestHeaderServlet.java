package servlet.servletmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
    }

    // Http Start line 정보
    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); // HTTP 메서드
        System.out.println("request.getProtocol() = " + request.getProtocol()); // HTTP 버전

        System.out.println("request.getScheme() = " + request.getScheme()); // http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " +
                           request.getQueryString()); // 쿼리 스트링
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    // Header 모든 정보
    private void printHeaders(HttpServletRequest request){
        System.out.println("--- HTTP 헤더 로그 시작 ---");

//        // 옛날 방식의 헤드 정보 가져오는 방법
//        // Enumeration을 통해 반복하여 각각의 헤더를 순회
//        Enumeration<String> headerNames = request.getHeaderNames(); // 헤더의 이름 리스트 모두 호출
//        while (headerNames.hasMoreElements()){  // 각각의 헤더들을 순회
//            String headerName = headerNames.nextElement();  // 요소 저장
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }

request.getHeaderNames().asIterator()
        .forEachRemaining(headerName -> System.out.println(headerName+": "+ headerName));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }
}
