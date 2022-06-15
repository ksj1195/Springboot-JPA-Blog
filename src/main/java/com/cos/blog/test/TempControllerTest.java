package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.cos.blog.test
 * fileName : TempControllerTest
 * author : 강수정
 * date : 2022-06-05
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-05         강수정          최초 생성
 */
@Controller
public class TempControllerTest {
    
    // * test 전 application.yml - spring 주석

    // http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");

        // * 파일리턴 기본경로 : src/main/resources/static
        // 리턴명 : /home.html
        // 풀 경로 : src/main/resources/static/home.html
        // static = 정적인 파일만 찾는다.

        // @RestController는 문자 그 자체를 return 하는 반면,
        // @Controller는 해당 경로 이하에 있는 파일을 리턴하므로 슬러시(/)를 붙여야 한다.
        return "/home.html";
    }


    // http://localhost:8000/blog/temp/img
    @GetMapping("/temp/img")
    public String tempImg() {
        return "/img.jpg";
    }


    // http://localhost:8000/blog/temp/jsp1
    // static은 정적인 파일만 찾는다. JSP는 동적인 파일이기 때문에 찾지못한다.
    @GetMapping("/temp/jsp1")
    public String tempJsp1() {
        return "/test.jsp1";
    }

    // http://localhost:8000/blog/temp/jsp2
    // static은 정적인 파일만 찾는다. JSP는 동적인 파일이기 때문에 찾지못한다.
    // => src/main/webapp/WEB-INF/views 폴더를 생성한 후 넣는다.
    // * application.yml - spring 주석 해제
    @GetMapping("/temp/jsp2")
    public String tempJsp2() {
        // prefix : /WEB-INF/views/   <- 앞에 붙여주는 경로명
        // suffix : .jsp              <- 뒤에 붙여주는 경로명
        // 풀네임 : /WEB-INF/views/test.jsp
        return "test";
    }

    // 스프링부트는 기본적으로 jsp 지원을 하지 않는다.
    //jsp파일을 리턴해줘도 정상적으로 동작하지 않는다.
    //스프링부트에서 jsp를 인식하기 위한 사전설정은 다음과 같다.
    //
    //    1. pom.xml에 JSP 템플릿 엔진 dependency 추가
    //    		<!-- JSP 템플릿 엔진 -->
    //		<dependency>
    //		    <groupId>org.apache.tomcat.embed</groupId>
    //		    <artifactId>tomcat-embed-jasper</artifactId>
    //		</dependency>
    //
    //    2. application.yml에 다음 코드를 추가한다.
    //        spring:
    //          mvc:
    //            view:
    //              prefix: /WEB-INF/views/
    //              suffix: .jsp
    //
    //       src/main/webapp/WEB-INF/views 디렉토리를 직접 만들어야 한다.
    //       이때 뒤에 확장자명이 이미 설정파일에 추가되어 있으므로 파일이름만을 return한다. EX) return "temp";
    //
    //JSP파일은 정적 파일이 아니므로 웹서버인 아파치가 처리하지 못한다. 톰캣이 대신 컴파일해서 웹브라우저에게 전달한다.
}
