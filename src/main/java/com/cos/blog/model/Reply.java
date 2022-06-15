package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * packageName : com.cos.blog.model
 * fileName : Reply
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
@Entity // @Entity : User 클래스가 자동으로 MySQL에 테이블이 생성된다.
// ORM -> java(포함 다른언어) Object를 테이블로 매핑해주는 기술
public class Reply {

    // @Id :primary key
    // @GeneratedValue : 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    //                  ex) 오라클을 사용한다 => 시퀀스 사용, MySQL을 사용한다 => auto_increment 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    // content가 null이 될 수 없고, 길이가 200자 이하여야 함.
    @Column(nullable = false, length = 200)
    private String content;


    // 하나의 게시글에는 여러개의 댓글이 달릴 수 있음.
    @ManyToOne // Many = reply, One = board
    // 어느 게시글의 댓글인지? 연관관계 필요
    @JoinColumn(name = "boardId")
    private Board board;


    // 여러개의 댓글을 한명의 유저가 쓸 수 있음.
    @ManyToOne
    // 누가 적은 댓글인지? 연관관계 필요
    @JoinColumn(name = "userId")
    private User user;


    // @CreationTimestamp : 시간이 자동입력 됨
    @CreationTimestamp
    private Timestamp createDate;
}
