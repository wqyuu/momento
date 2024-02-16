package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.entity.Tag;

/**
 * 标签;(tag)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2022-6-28
 */
public interface TagService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Tag queryById(String id);
    
    /**
     * 分页查询
     *
     * @param tag 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Tag> paginQuery(Tag tag, long current, long size);
    /** 
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag insert(Tag tag);
    /** 
     * 更新数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag update(Tag tag);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
}