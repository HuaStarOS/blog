package com.qzh.dao;

import com.qzh.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/27 15:14
 */
public interface CommentDao extends JpaRepository<Comment, Long> {

    /**
     * 通过博客ID查询该博客的所有评论
     * @param id
     * @param sort
     * @return
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long id, Sort sort);

}
