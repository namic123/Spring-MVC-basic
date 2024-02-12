# HTTP 요청 데이터 전송 방식

## 크게 3가지 방법으로 사용 (요청 데이터 전송은 일반적으로 아래 3가지를 벗어나지 않는다 )

## GET - query parameter 사용
* 예: /url?username=park&age=29
* 요청 메시지 바디 없이, URL의 쿼리파라미터에 데이터를 포함 전달
* 검색, 필터, 페이징 등에서 많이 사용

## POST - HTML form을 통해 데이터 전송
* content-type: application/x-www-form-urlencoded
* 요청 메시지 바디에 쿼리파라미터 형식으로 전달 : username=park&age=29
* 회원 가입, 상품 주문 등을 HTML form을 통해 전송

## HTTP message body에 데이터를 직접 담아서 요청
* HTTP API(REST API)에서 주로 사용, JSON, XML, TEXT
* 데이터 형식은 주로 JSON 사용
  * post, put, patch
