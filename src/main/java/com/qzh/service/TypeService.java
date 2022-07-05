package com.qzh.service;


import com.qzh.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/25 15:02
 */
public interface TypeService {

    /**
     * 新增一个分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据id查询对应的分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 查询分类列表
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 查询所有分类
     * @return
     */
    List<Type> listType();

    /**
     * 查询博客最多的分类
     * @return
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 修改分类
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 通过名称查找分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 删除分类
     * @param id
     */
    void deleteType(Long id);

}
