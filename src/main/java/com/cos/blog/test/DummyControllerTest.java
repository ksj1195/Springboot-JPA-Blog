package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

/**
 * packageName : com.cos.blog.test
 * fileName : DummyControllerTest
 * author : 강수정
 * date : 2022-06-05
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-05         강수정          최초 생성
 */

@RestController
// Controller : 사용자가 요청 -> 응답(HTML)
// RestController : 사용자가 요청 -> 응답(data=json)
public class DummyControllerTest {

    // 스프링이 메모리에 로딩한 객체를 해당 변수에 집어넣어준다. = 의존성 주입(DI)
    @Autowired
    private UserRepository userRepository; // 객체정의(null) -> @Autowired에 의해 객체 들어옴


    // * 삭제하기
    // http://localhost:8000/blog/dummy/user/1
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 존재하지 않는 유저입니다.";
        }

        return "삭제 되었습니다. id : " +id;
    }


    // * 유저정보 수정하기(email, password 수정)
    // http://localhost:8000/blog/dummy/user/1
    // @Transactional : save 하지 않아도 update가 된다. 함수 종료시에 자동으로 commit
    // 영속성 컨텍스트가 변경된 데이터 감지 -> DB 데이터 수정 = 더티체킹
    @Transactional
    @PutMapping("/dummy/user/{id}")
    // json 데이터 받으려면 @RequestBody 필요
    // json 데이터 요청 => 스프링의 MessageConverter의 Jackson 라이브러리가 java object로 변환해서 받아옴
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // save 함수는 id를 전달하지 않으면 insert
        // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있을 때 update
        // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없을 때 insert

//        userRepository.save(user);
        return user;
    }


    // * 모든 유저 조회
    // http://localhost:8000/blog/dummy/users
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }


    // * 한 페이지당 2건의 데이터 리턴
    @GetMapping("/dummy/user")
    // http://localhost:8000/blog/dummy/user
    // http://localhost:8000/blog/dummy/user?page=0 (1페이지)
    // 2건씩, sort는 id로, id는 최신순으로 정렬
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }


    // * id로 select 하기
    // {id} 주소로 파라메터를 전달받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // findById의 return 타입이 Optional임. 왜?
        // User/4를 찾을 때 내가 DB에서 못찾게되면 user가 null이 될 것 아냐?
        // 그럼 return 값이 null이 되잖아? 그럼 프로그램에 문제가 있지 않겠니?
        // 그래서 Optional로 너의 user 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!
        // orElseGet : 없으면 객체 하나 만들어서 user에 넣어줘. 그럼 null이 아닐거잖아?
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            // 찾는 data가 없을 때 아래 메소드가 실행
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id);
            }
        });
        // 요청 : 웹 브라우저
        // user 객체 = java 오브젝트, 웹 브라우저는 객체를 이해못함
        // 변환 필요(웹 브라우저가 이해할 수 있는 데이터 -> JSON)
        // 스프링부트는 MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson이라는 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }

//    람다식 사용할 경우
//    User user = userRepository.findById(id).orElseThrow(()->{
//        return new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id);
//    })


    // * 회원가입(post)
    // http://localhost:8000/blog/dummy/join (요청)
    // http의 바디에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
