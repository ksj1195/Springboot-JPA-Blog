package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * packageName : com.cos.blog.repository
 * fileName : UserRepository
 * author : 강수정
 * date : 2022-06-05
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-05         강수정          최초 생성
 */

// JpaRepository는 User 테이블을 관리하는 Repository이다. user 테이블의 PK는 integer(숫자)이다.
// JpaRepository는 findAll 등의 메소드를 가지고 있다. 기본적인 CRUD 기능은 아래처럼 정의하면 모두 가능.
// DAO이라고 생각하면 됨.
// 자동으로 Bean 등록 됨. @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
}

// JPA Naming 전략
// SELECT * FROM user WHERE uesrname = ?1 AND password =?2;
// User findByUsernameAndPassword(String uesrname, String password);

//@Query(value = "SELECT * FROM uesr WHERE username =?1 AND password =?2", nativeQuery = true)
//User login(String username, String password);



