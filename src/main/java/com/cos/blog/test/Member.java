package com.cos.blog.test;

import lombok.*;

/**
 * packageName : com.cos.blog.test
 * fileName : Member
 * author : 강수정
 * date : 2022-06-04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-04         강수정          최초 생성
 */

//@Getter                // Getter 자동생성(Lombok 기능)
//@Setter                // Setter 자동생성(Lombok 기능)
@Data                   // Getter,Setter 한꺼번에 자동생성(Lombok 기능)
@NoArgsConstructor      // 빈 생성자(기본 생성자)(Lombok 기능)
//@AllArgsConstructor   // 모든 변수를 매개변수로 하는 생성자 자동생성(Lombok 기능)
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder // @Builder : 매개변수 3개짜리 생성자가 필요할 때, 따로 만들지 않아도 되게 해준다.
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}


