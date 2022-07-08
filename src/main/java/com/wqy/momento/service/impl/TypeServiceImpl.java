package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wqy.momento.entity.Type;
import com.wqy.momento.mapper.TypeMapper;
import com.wqy.momento.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

 /**
 * 类别;(type)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2022-6-28
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(String id){
        return typeMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param type 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    @Override
    public Page<Type> paginQuery(Type type, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(type.getTenantId())){
            queryWrapper.eq(Type::getTenantId, type.getTenantId());
        }
        if(StrUtil.isNotBlank(type.getRevision())){
            queryWrapper.eq(Type::getRevision, type.getRevision());
        }
        if(StrUtil.isNotBlank(type.getCreatedBy())){
            queryWrapper.eq(Type::getCreatedBy, type.getCreatedBy());
        }
        if(StrUtil.isNotBlank(type.getUpdatedBy())){
            queryWrapper.eq(Type::getUpdatedBy, type.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(type.getId())){
            queryWrapper.eq(Type::getId, type.getId());
        }
        if(StrUtil.isNotBlank(type.getCode())){
            queryWrapper.eq(Type::getCode, type.getCode());
        }
        if(StrUtil.isNotBlank(type.getName())){
            queryWrapper.eq(Type::getName, type.getName());
        }
        //2. 执行分页查询
        Page<Type> pagin = new Page<>(current , size , true);
        IPage<Type> selectResult = typeMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type){
        typeMapper.insert(type);
        return type;
    }
    
    /** 
     * 更新数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Type> chainWrapper = new LambdaUpdateChainWrapper<Type>(typeMapper);
        if(StrUtil.isNotBlank(type.getTenantId())){
            chainWrapper.eq(Type::getTenantId, type.getTenantId());
        }
        if(StrUtil.isNotBlank(type.getRevision())){
            chainWrapper.eq(Type::getRevision, type.getRevision());
        }
        if(StrUtil.isNotBlank(type.getCreatedBy())){
            chainWrapper.eq(Type::getCreatedBy, type.getCreatedBy());
        }
        if(StrUtil.isNotBlank(type.getUpdatedBy())){
            chainWrapper.eq(Type::getUpdatedBy, type.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(type.getId())){
            chainWrapper.eq(Type::getId, type.getId());
        }
        if(StrUtil.isNotBlank(type.getCode())){
            chainWrapper.eq(Type::getCode, type.getCode());
        }
        if(StrUtil.isNotBlank(type.getName())){
            chainWrapper.eq(Type::getName, type.getName());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Type::getCode, type.getCode());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(type.getCode());
        }else{
            return type;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param undefinedId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String undefinedId){
        int total = typeMapper.deleteById(undefinedId);
        return total > 0;
    }
}