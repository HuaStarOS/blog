package com.qzh.dao;

import com.qzh.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/25 15:11
 */
public interface TypeDao extends JpaRepository<Type,Long> {

    /**
     * 通过名称查找分类
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 查询博客最多的多个分类
     * @param pageable
     * @return
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
