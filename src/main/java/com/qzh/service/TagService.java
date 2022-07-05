package com.qzh.service;

import com.qzh.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/25 19:52
 */
public interface TagService {

    /**
     * 添加标签
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 通过id查询标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 查询标签列表
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTag();

    /**
     * 查询博客最多的标签
     * @return
     */
    List<Tag> listTagTop(Integer size);

    /**
     * 通过多个Id值查询多个标签
     * @param ids
     * @return
     */
    List<Tag> listTag(String ids);


    /**
     * 更新标签
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id,Tag tag);

    /**
     * 通过名称查询标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 通过id删除标签
     * @param id
     */
    void deleteTag(Long id);

}
