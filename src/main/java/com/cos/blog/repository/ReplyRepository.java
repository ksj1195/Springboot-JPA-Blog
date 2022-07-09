package com.cos.blog.repository;

import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.cos.blog.repository
 * fileName : ReplyRepository
 * author : ksj
 * date : 2022/07/08
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/08         ksj          최초 생성
 */
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
}
