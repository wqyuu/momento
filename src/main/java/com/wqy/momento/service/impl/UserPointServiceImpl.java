package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wqy.momento.entity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.wqy.momento.entity.UserPoint;
import com.wqy.momento.mapper.UserPointMapper;
import com.wqy.momento.service.UserPointService;
 /**
 * 用户积分;(user_point)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Service
public class UserPointServiceImpl implements UserPointService{
    @Autowired
    private UserPointMapper userPointMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public UserPoint queryById(Integer id){
        return userPointMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param userPoint 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<UserPoint> paginQuery(UserPoint userPoint, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<UserPoint> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(userPoint.getTenantId())){
            queryWrapper.eq(UserPoint::getTenantId, userPoint.getTenantId());
        }
        if(StrUtil.isNotBlank(userPoint.getRevision())){
            queryWrapper.eq(UserPoint::getRevision, userPoint.getRevision());
        }
        if(StrUtil.isNotBlank(userPoint.getCreatedBy())){
            queryWrapper.eq(UserPoint::getCreatedBy, userPoint.getCreatedBy());
        }
        if(StrUtil.isNotBlank(userPoint.getUpdatedBy())){
            queryWrapper.eq(UserPoint::getUpdatedBy, userPoint.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(userPoint.getUserId())){
            queryWrapper.eq(UserPoint::getUserId, userPoint.getUserId());
        }
        //2. 执行分页查询
        Page<UserPoint> pagin = new Page<>(current , size , true);
        IPage<UserPoint> selectResult = userPointMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param userPoint 实例对象
     * @return 实例对象
     */
    public UserPoint insert(UserPoint userPoint){
        userPointMapper.insert(userPoint);
        return userPoint;
    }
    
    /** 
     * 更新数据
     *
     * @param userPoint 实例对象
     * @return 实例对象
     */
    public UserPoint update(UserPoint userPoint){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<UserPoint> chainWrapper = new LambdaUpdateChainWrapper<UserPoint>(userPointMapper);
        if(StrUtil.isNotBlank(userPoint.getTenantId())){
            chainWrapper.eq(UserPoint::getTenantId, userPoint.getTenantId());
        }
        if(StrUtil.isNotBlank(userPoint.getRevision())){
            chainWrapper.eq(UserPoint::getRevision, userPoint.getRevision());
        }
        if(StrUtil.isNotBlank(userPoint.getCreatedBy())){
            chainWrapper.eq(UserPoint::getCreatedBy, userPoint.getCreatedBy());
        }
        if(StrUtil.isNotBlank(userPoint.getUpdatedBy())){
            chainWrapper.eq(UserPoint::getUpdatedBy, userPoint.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(userPoint.getUserId())){
            chainWrapper.eq(UserPoint::getUserId, userPoint.getUserId());
        }
        //2. 设置主键，并更新
        chainWrapper.set(UserPoint::getId, userPoint.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(userPoint.getId());
        }else{
            return userPoint;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        int total = userPointMapper.deleteById(id);
        return total > 0;
    }

     @Override
     public UserPoint selectByUser(String userId) {
         LambdaQueryWrapper<UserPoint> queryWrapper = new LambdaQueryWrapper<>();
         if(StrUtil.isNotBlank(userId)){
             queryWrapper.eq(UserPoint::getUserId, userId);
         }
         return userPointMapper.selectOne(queryWrapper);
     }
 }