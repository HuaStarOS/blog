package com.qzh.dao;

import com.qzh.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/25 19:50
 */
public interface TagDao extends JpaRepository<Tag,Long> {

    /**
     * 通过名称查询标签
     * @param name
     * @return
     */
    Tag findByName(String name);

    /**
     * 查询博客最多的标签
     * @param pageable
     * @return
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
