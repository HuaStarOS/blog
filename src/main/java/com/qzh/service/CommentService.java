package com.qzh.service;

import com.qzh.pojo.Comment;

import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/27 15:15
 */
public interface CommentService {

    /**
     * 通过博客ID查询该博客的所有评论
     *
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
