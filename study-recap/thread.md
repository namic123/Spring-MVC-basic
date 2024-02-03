# 동시 요청 - 멀티 쓰레드
* 클라이언트로부터 요청이 들어오면 WAS에서 아래와 같은 과정을 거친다.
1. TCP/IP 연결: 클라이언트의 요청이 들어오면, 서버는 TCP/IP 프로토콜을 사용하여 네트워크를 통해 데이터를 전송하고 받는 소켓 연결을 수립
2. 쓰레드 할당: WAS는 요청을 처리할 새로운 쓰레드를 할당하거나, 쓰레드 풀에서 대기 중인 쓰레드를 재사용하여 요청을 처리
3. 서블릿 객체 호출: 쓰레드는 서버 구성에 따라 요청 URL과 매핑된 서블릿 객체를 찾아 'service'메서드를 호출한다. 

## Thread(쓰레드)
쓰레드는 애플리케이션 코드를 하나씩 순차적으로 실행한다. Java로 예를 들어, 메인 메서드를 실행하면 main이라는 이름의 쓰레드가 실행되는 것이다.
즉, 쓰레드가 없다면 자바 애플리케이션 실행이 불가능하고, 하나의 쓰레드는 "한번에 하나의 코드 라인"만을 수행한다. 
동시 처리가 필요한 경우 쓰레드를 추가로 생성해야한다. 

[단일 요청 쓰레드 - 쓰레드 한개 사용]
* 단일 요청이 올 경우, 하나의 쓰레드가 할당이 되며 해당하는 Servlet 객체를 호출하여 코드를 실행
* 응답 후, 쓰레드 휴식 

[다중 요청 - 쓰레드 한개 사용]
* 쓰레드 한개로 다중 요청을 처리하게되면, 해당 쓰레드가 요청을 처리완료 할 때까지 다른 요청은 쓰레드 대기 상태가된다.
* 이 경우, 처리중인 요청에서 처리 지연으로 쓰레드 타임아웃이 날 경우, 다른 모든 요청에도 에러가 발생하는 문제점이 발생할 수 있음. 

[요청마다 쓰레드 생성]
* 위와 같은 문제를 해결하기 위해 요청마다 신규 쓰레드를 생성 
* 그러나 요청 마다 쓰레드를 생성하는 방식은 장단점이 있다.
  * 장점
    * 동시 요청 처리 가능
    * 리소스(CPU, 메모리)가 허용할 때 까지 처리가능
    * 하나의 쓰레드가 지연되어도, 나머지 쓰레드는 정상 동작
  * 단점
    * 쓰레드의 생성 비용이 매우 비싸다.
      * 고객 요청이 올 때마다 쓰레드를 생성하면, 응답 속도가 늦어짐
    * 쓰레드는 컨텍스트 스위칭 비용이 발생(운영체제가 CPU의 실행을 한 쓰레드에서 다른 쓰레드로 전환하는 과정에서 발생되는 비용)
    * 쓰레드 생성에 제한이 없다.
      * 고객 요청이 너무 많이 오면, CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있음. 

[쓰레드 풀 - 요청 마다 쓰레드 생성의 단점을 보완]