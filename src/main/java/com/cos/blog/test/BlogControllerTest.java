package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : com.cos.blog.test
 * fileName : BlogControllerTest
 * author : 강수정
 * date : 2022-05-30
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-30         강수정          최초 생성
 */
@RestController
// ComponentScan : 스프링이 com.cos.blog 패키지 이하를 모두 스캔. 모든 파일을 메모리에 new하는 것은 아니고,
// 특정 어노테이션이 붙어있는 클래스 파일들을 new 해서(IoC) 스프링 컨테이너에 관리해준다.
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>hello spring boot</h1>";
    }
}
