package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

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

public interface BoardRepository extends JpaRepository<Board, Integer> {


}



