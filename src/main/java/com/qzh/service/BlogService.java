package com.qzh.service;

import com.qzh.pojo.Blog;
import com.qzh.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author: qzh
 * @Date: 2020/12/26 10:29
 */
public interface BlogService {

    /**
     * 通过id查询博客文章
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 将博客中的content的MarkDown文本转换成HTML文本
     * @param id
     * @return
     */
    Blog getAndConvert(Long id);

    /**
     * 通过标题和分类查询博客列表
     * @param pageable
     * @param blog 将标题和分类封装成Blog对象
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 根据tagId进行查询
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * 查询博客
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 查询标题和内容包含query的博客
     * @param query
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     * 查询最新的推荐博客
     * @param size
     * @return
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 根据年份查询博客
     * @return
     */
    Map<String, List<Blog>>  archiveBlog();

    /**
     * 查询博客总数目
     * @return
     */
    Long countBlog();

    /**
     * 新增博客
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 修改博客
     * @param id 通过id查询出要修改的博客
     * @param blog  将要修改的内容封装成blog对象
     * @return
     */
    Blog updateBlog(Long id,Blog blog);

    /**
     * 通过id删除博客
     * @param id
     */
    void deleteBlog(Long id);
}
