package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.entity.UserPoint;

 /**
 * 用户积分;(user_point)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
public interface UserPointService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPoint queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param userPoint 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<UserPoint> paginQuery(UserPoint userPoint, long current, long size);
    /** 
     * 新增数据
     *
     * @param userPoint 实例对象
     * @return 实例对象
     */
    UserPoint insert(UserPoint userPoint);
    /** 
     * 更新数据
     *
     * @param userPoint 实例对象
     * @return 实例对象
     */
    UserPoint update(UserPoint userPoint);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}