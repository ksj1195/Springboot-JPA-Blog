package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * packageName : com.cos.blog.model
 * fileName : User
 * author : 강수정
 * date : 2022-06-05
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-05         강수정          최초 생성
 */

@Data  // Getter, Setter 자동생성
@NoArgsConstructor // 빈 생성자 자동생성
@AllArgsConstructor // 모든 변수를 매개변수로 하는 생성자 자동생성
@Builder // 필요한 매개변수만 사용할 수 있게 함
// @DynamicInsert // insert 할 때 null인 필드 제외시켜준다.
@Entity // @Entity : User 클래스가 자동으로 MySQL에 테이블이 생성된다.
// ORM -> java(포함 다른언어) Object를 테이블로 매핑해주는 기술
public class User {

    // @Id :primary key
    // @GeneratedValue : 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    //                  ex) 오라클을 사용한다 => 시퀀스 사용, MySQL을 사용한다 => auto_increment 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스(오라클), auto_increment(MySQL)


    // username이 null이 될 수 없고, 길이가 30자 이하여야 함.
    @Column(nullable = false, length = 30, unique = true)
    private String username; // 사용자 이름


    // password가 null이 될 수 없고, 길이가 100자 이하여야 함.
    @Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
    private String password; // 비밀번호


    // email이 null이 될 수 없고, 길이가 50자 이하여야 함.
    @Column(nullable = false, length = 50)
    private String email; // 이메일


    // @ColumnDefault("'user'") // 문자열은 홑따옴표
    // DB는 RoleType이라는게 없다. 해당 Enum이 String이라는 것을 알려줘야함.
    @Enumerated(EnumType.STRING)
    private RoleType role; // role : 권한(ADMIN, USER, MANAGER...) 오타실수를 방지하기 위해 Enum을 쓰는게 좋다.


    // @CreationTimestamp : 시간이 자동입력 됨
    // 내가 직접 시간을 넣으려면 TimeStamp.valueOf(LocalDateTime.now())
    @CreationTimestamp
    private Timestamp createDate; // 가입시간
}
