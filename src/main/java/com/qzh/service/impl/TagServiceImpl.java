package com.qzh.service.impl;

import com.qzh.dao.TagDao;
import com.qzh.exception.NotFoundException;
import com.qzh.pojo.Tag;
import com.qzh.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qzh
 * @Date: 2020/12/25 19:52
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getOne(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable= PageRequest.of(0,size,sort);
        return tagDao.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagDao.findAllById(convertLong(ids));
    }

    private List<Long> convertLong(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(new Long(split[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag one = tagDao.getOne(id);
        if (one == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag, one);
        return tagDao.save(one);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }
}
