package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.cos.blog.controller
 * fileName : BoardController
 * author : 강수정
 * date : 2022-06-06
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-06         강수정          최초 생성
 */

@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션을 어떻게 찾는지?
        // prefix: /WEB-INF/views/
        // suffix: .jsp
        // 풀경로: /WEB-INF/views/index.jsp
        System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
        return "index";
    }
}
