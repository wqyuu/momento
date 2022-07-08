package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.entity.Momento;

/**
 * momento;(momento)表服务接口
 * @author : wqy
 * @date : 2022-6-28
 */
public interface MomentoService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Momento queryById(String id);
    
    /**
     * 分页查询
     *
     * @param momento 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Momento> paginQuery(Momento momento, long current, long size);
    /** 
     * 新增数据
     *
     * @param momento 实例对象
     * @return 实例对象
     */
    Momento insert(Momento momento);
    /** 
     * 更新数据
     *
     * @param momento 实例对象
     * @return 实例对象
     */
    Momento update(Momento momento);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
}