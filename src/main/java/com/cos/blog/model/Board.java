package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * packageName : com.cos.blog.model
 * fileName : Board
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
public class Board {

    // @Id :primary key
    // @GeneratedValue : 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    //                  ex) 오라클을 사용한다 => 시퀀스 사용, MySQL을 사용한다 => auto_increment 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, length = 100)
    private String title;


    // @Lob : 대용량 데이터를 쓸 때 사용
    @Lob
    private String content; // 섬머노트 라이브러리 사용 => <html>태그가 섞여서 디자인되면 용량이 커짐


    // 조회수 기본값 : 0(int는 홑따옴표 필요X)
    @ColumnDefault("0")
    private int count; // 조회수


    // 필드값은 userId, 연관관계는 ManyToOne. PK 참조
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, One = User : 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="userId") // Entity 될때 필드값이 userId 으로 생성됨
    private User user; //DB는 오브젝트를 저장할 수 없다. FK 사용. 자바는 오브젝트를 저장할 수 있다.


    // 하나의 게시글은 여러개의 댓글을 가질 수 있다. One = board, Many = reply
    // mappedBy : 연관관계의 주인이 아니다.(난 FK가 아니에요. DB에 컬럼을 만들지 마세요.)
    //            board를 select 할 때 join을 통해 값을 얻기위해서 필요한 것
    // reply 테이블의 board가 FK임.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // reply 클래스의 board 필드
    // 댓글은 하나일수도, 여러개일수도 있기 떄문에 List 사용
    private List<Reply> reply;


    // @CreationTimestamp : 시간이 자동입력 됨
    @CreationTimestamp
    private Timestamp createDate;
}
