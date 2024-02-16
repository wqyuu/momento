package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.entity.Timing;

 /**
 * 时机;(timing)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
public interface TimingService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Timing queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param timing 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Timing> paginQuery(Timing timing, long current, long size);
    /** 
     * 新增数据
     *
     * @param timing 实例对象
     * @return 实例对象
     */
    MomentResponse insert(Timing timing);
    /** 
     * 更新数据
     *
     * @param timing 实例对象
     * @return 实例对象
     */
    Timing update(Timing timing);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}