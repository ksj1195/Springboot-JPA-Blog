package com.cos.blog.model;

/**
 * packageName : com.cos.blog.model
 * fileName : RoleType
 * author : 강수정
 * date : 2022-06-05
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-05         강수정          최초 생성
 */

// Enum을 만들면 내가 넣는 값의 타입을 강제할 수 있음.(실수를 방지)
// Enum는 데이터의 도메인(범위)를 만들 때 쓴다. ex) 성별은 남자,여자 두 값만 가질 수 있다.
public enum RoleType {
    USER, ADMIN
}
