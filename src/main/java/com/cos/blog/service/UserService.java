package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * packageName : com.cos.blog.service
 * fileName : UserService
 * author : 강수정
 * date : 2022-06-08
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-08         강수정          최초 생성
 */

// 스프링이 컴포넌트 스캔을 통해서 Bean에 객체 등록(Ioc)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화 된 User 오브젝트를 수정
        // Select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화 하기 위함
        // 영속화 된 오브젝트를 변경하면 자동으로 DB에 update문 날려줌
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });

        // Validate 체크 -> oauth 필드에 값이 없으면 수정 가능, 있으면 수정 불가능
        if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }

        // 회원수정 함수 종료시 = 서비스 종료시 = 트랜젝션 종료 = 자동으로 commit 됨
        // 영속화 된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문 날려줌.
    }
}
