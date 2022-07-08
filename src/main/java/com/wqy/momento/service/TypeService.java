package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.entity.Type;

/**
 * 类别;(type)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2022-6-28
 */
public interface TypeService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param undefinedId 主键
     * @return 实例对象
     */
    Type queryById(String undefinedId);
    
    /**
     * 分页查询
     *
     * @param type 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Type> paginQuery(Type type, long current, long size);
    /** 
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type insert(Type type);
    /** 
     * 更新数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type update(Type type);
    /** 
     * 通过主键删除数据
     *
     * @param undefinedId 主键
     * @return 是否成功
     */
    boolean deleteById(String undefinedId);
}