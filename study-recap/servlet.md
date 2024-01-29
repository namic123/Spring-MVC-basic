# 서블릿 (Servlet)

## WAS를 직접 구현하는 경우
* 1. 서버 TCP/IP연결 대기, 소켓 연결: 클라이언트의 연결 요청을 대기하고 소켓을 통해 연결한다.
* 2. HTTP 요청 메시지를 파싱해서 읽기 : 클라이언트로부터 받은 HTTP 요청을 메시지로 파싱하여 읽는다.
* 3. POST 방식, /save URL 인지: 즉, HTTP 요청 메서드가 무엇인지, 요청 URL의 경로를 인지하는 작업.
* 4. Content-Type 확인
* 5. HTTP 메시지 바디 내용 파싱
    * 예를 들어 JSON 형식의 데이터를 Java객체로 파싱하는 작업
* 6. 저장 프로세스 실행
* 7. 비즈니스 로직 실행
    * DB에 저장 요청
* 8. HTTP 응답 메시지 생성 시작
    * HTTP 시작 라인 생성
    * header 생성
    * 메시지 바디에 HTML 생성에서 입력
* 9. TCP/IP에 응답 전달 및 소켓 종료

## 서블릿을 지원하는 WAS를 사용하는 경우
* 위 과정을 다 WAS에서 지원하며, 개발자는 7번째인 비지니스 로직만을 신경써서 개발하면 된다.

## 서블릿 컨테이너
* 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 한다.
* 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리를한다.
* 서블릿 객체는 싱글톤으로 관리된다.
    * 예를들어 고객 요청마다 객체를 생성하는 것은 비효율
    * 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용
    * 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
    * 단, 공유 변수(static) 사용은 주의
* 동시 요청을 위한 멀티 쓰레드 처리 지원

# Servlet을 지원하는 WAS의 HTTP 요청 처리 흐름
## 요청 흐름
1. 웹 브라우저에서 요청 발생 (HTTP 요청 메세지 생성)
2. WAS에서 요청 수신
3. WAS는 요청을 적절한 서블릿 컨테이너로 전달
4. HttpServletRequest 객체 생성 : 서블릿 컨테이너는 Http 요청 메시지를 기반으로 HttpServletRequest 객체를 생성한다.
5. 서블릿 매핑과 실행 : 서블릿 컨테이너는 요청 URL을 기반으로 적절한 서블릿을 찾아 실행한다. 이때 HttpServletRequest와 HttpServletResponse 객체를 파라미터로 전달

```java
@WebServlet("name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response){
        // 애플리케이션 로직
        }    
}
```
* 위 코드에서 @WebServlet 애너테이션은 이 클래스가 서블릿이라는 것을 나타냄
* name 은 서블릿을 이름을 지정하는 것
* urlPatterns는 이 서블릿이 반응할 URL 패턴을 지정함.
* service 메서드에서 HttpServletRequest 서블릿 컨테이너가 http 요청 메시지를 기반으로 만든 객체
    * 즉, 요청 정보를 담고 있다.
* HttpServletResponse 클라이언트에게 보낼 응답을 담는 객체


## 응답 흐름
1. 서블릿 로직 실행 : 서블릿 로직을 실행하고, 사용자에게 보낼 응답을 HttpServletResponse객체에 저장한다.
2. (서블릿 컨테이너)응답 객체로 HTTP 응답 생성: 서블릿 컨테이너는 HttpServletResponse 객체의 정보를 기반으로 HTTP 응답 메세지를 생성한다.
3. WAS에서 응답을 전송: 생성된 HTTP 응답 메시지는 다시 WAS를 통해 웹 브라우저에 전송된다.
4. 서블릿 종료: 요청과 응답 처리가 완료되면 서블릿은 종료된다.

## 요청과 흐름 정리
* 1. HTTP 요청이 들어오면 WAS는 HttpServletRequest와 HttpServletResponse 객체를 만들어서 서블릿 객체를 호출한다.
* 2. 개발자는 HttpServletRequest 객체에 HTTP 요청 정보를 편리하게 꺼내서 사용한다.
* 3. 개발자는 HttpServletResponse 객체에 HTTP 응답 정보를 편리하게 입력한다.
* 4. WAS는 HttpServletResponse 객체에 담겨 있는 내용으로 HTTP 응답 정보를 생성한다. 