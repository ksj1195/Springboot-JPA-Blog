package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

/**
 * packageName : com.cos.blog.test
 * fileName : HttpControllerTest
 * author : 강수정
 * date : 2022-06-04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-04         강수정          최초 생성
 */
@RestController
// Controller : 사용자가 요청 -> 응답(HTML)
// RestController : 사용자가 요청 -> 응답(data=json)
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest";

    @GetMapping("/http/lombok")
    public String lombokTest() {
//        Member m = new Member(1, "oocul", "1234", "oocul@naver.com");
        // 만약 id는 시퀀스로 DB에서 받아오고 싶다. ID를 제외한 값만 넣고싶을 때 Builder 사용
        // 객체에 값을 넣을때 순서를 지키지 않아도 됨. 순서 헷갈리는 실수에 의한 에러 방지.
        Member m = Member.builder().username("oocul").password("1234").email("oocul@naver.com").build();

        System.out.println(TAG + " getter : " + m.getUsername());
        m.setUsername("oocul1195");
        System.out.println(TAG + " setter : " + m.getUsername());

        return "lombok test 완료";
    }

    // * ------------------------------ Get(select) ------------------------------
    // 인터넷 브라우저에서 요청은 무조건 get 요청만 가능
    // http://localhost:8080/http/get (select)
    // @RequestParam을 넣으면 브라우저에서 쿼리스트링으로 값 받을 수 있음
    // 매개변수 하나하나 @RequestParam을 달아줘야함
    // Member m 오브젝트에 쿼리값을 매핑 시켜주는 일을 누가한다? => MessageConverter(스프링부트)
    @GetMapping("/http/get1")
    public String getTest1(@RequestParam int id, @RequestParam String username) {
        return "get 요청 : " + id + "," + username;
    }
    
    // 매개변수를 객체로 받으면 @RequestParam 하나하나 달지 않아도 됨
    // http://localhost:8080/http/get?id=1&username=oocul
    @GetMapping("/http/get2")
    public String getTest2(Member m) {
        return "get 요청 : " + m.getId() + "," + m.getUsername();
    }


    // * ------------------------------ Post(insert) ------------------------------
    // http://localhost:8080/http/post (insert)
    // post는 데이터를 주소에 붙이는게 아니라 body에 담아서 보낸다.
    // 1. post => x-www-form-uriencoded
    @PostMapping("/http/post1")
    public String postTest1(Member m) {
        return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getEmail() + "," + m.getPassword();
    }

    // 2. post => raw(text)
    // raw(text) : mime type = text/plain
    // text는 말그대로 문자 그대로를 보여주는 것임
    // post는 데이터를 주소에 붙이는게 아니라 body에 담아서 보낸다. => Requestbody
    @PostMapping("/http/post2")
    public String postTest2(@RequestBody String text) {
        return "post 요청 : " + text;
    }

    // 3. post => raw(json)
    // raw(json) : mime type = application/json
    // post는 데이터를 주소에 붙이는게 아니라 body에 담아서 보낸다. => Requestbody
    // Member m 오브젝트에 쿼리값을 매핑 시켜주는 일을 누가한다? => MessageConverter(스프링부트)
    @PostMapping("/http/post3")
    public String postTest3(@RequestBody  Member m) {
        return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getEmail() + "," + m.getPassword();
    }


    // * ------------------------------ Put(update) ------------------------------
    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getEmail() + "," + m.getPassword();
    }


    // * ----------------------------- Delete(delete) ----------------------------
    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody Member m) {
        return "delete 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getEmail() + "," + m.getPassword();
    }



    // * --------------------------------- 정리 ------------------------------------

    // * 1. get 요청
    //    http://localhost:8000/blog/user?username=oocul
    //    이 때 key=value의 키값은 변수명과 정확히 일치해야함
    //    특징 : 주소에 담아서 브라우저를 통해 보낼 수 있고, body로 데이터를 담아 보내지 않음. form태그 형식
    // * 2. post, put, delete 요청 (데이터 변경)
    //    데이터를 담아보내야 할 것이 많음.(username, password, email, address...)
    //    form 태그로 가능하지만 get, post만 가능, put과 delete는 불가능(자바 스크립트 요청을 해야함)
    //    고로, 통일을 위해 javascript로 ajax요청 + 데이터는 json형태로 사용(수업에서는 이 방식 사용)
    //    또는, form:form 태그로 4가지 요청을 다 커버할 수도 있다.
    // * 3. 스프링 컨트롤러의 파싱 전략 1
    //    스프링 컨트롤러는 key=value 형태의 데이터를 자동으로 파싱하여 변수에 담아준다.
    //    가령 get 요청은 key=value이고 post 요청 중에 x-www-form-urlencoded(form 태그를 만들어서 데이터 전송)시에도
    //    key=value이기 때문에 이러한 데이터는 함수의 파라메터로 받을 수 있다.
    // * 4. 스프링 컨트롤러의 파싱 전략 2
    //    스프링은 key=value 형태의 데이터를 오브젝트로 파싱해서 받아주는 역할도 한다.
    //    이때 주의할 점은 setter가 없으면 key=value 데이터를 스프링이 파싱해서 넣어주지 못한다.(해당 object의 setter 꼭 필요)
    // * key=value가 아닌 데이터는 어떻게 파싱할까?
    //    key=value 형태가 아닌 Json 데이터나 일반 text 데이터는 @RequestBody 어노테이션이 필요하다.
}
