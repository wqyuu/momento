package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.wqy.momento.entity.Config;
import com.wqy.momento.mapper.ConfigMapper;
import com.wqy.momento.service.ConfigService;
 /**
 * 配置;(config)表服务实现类
 * @author : wqy
 * @date : 2022-6-28
 */
@Service
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    private ConfigMapper configMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Config queryById(String id){
        return configMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param config 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Config> paginQuery(Config config, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(config.getTenantId())){
            queryWrapper.eq(Config::getTenantId, config.getTenantId());
        }
        if(StrUtil.isNotBlank(config.getRevision())){
            queryWrapper.eq(Config::getRevision, config.getRevision());
        }
        if(StrUtil.isNotBlank(config.getCreatedBy())){
            queryWrapper.eq(Config::getCreatedBy, config.getCreatedBy());
        }
        if(StrUtil.isNotBlank(config.getUpdatedBy())){
            queryWrapper.eq(Config::getUpdatedBy, config.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(config.getName())){
            queryWrapper.eq(Config::getName, config.getName());
        }
        if(StrUtil.isNotBlank(config.getDetail())){
            queryWrapper.eq(Config::getDetail, config.getDetail());
        }
        if(StrUtil.isNotBlank(config.getTypeCode())){
            queryWrapper.eq(Config::getTypeCode, config.getTypeCode());
        }
        //2. 执行分页查询
        Page<Config> pagin = new Page<>(current , size , true);
        IPage<Config> selectResult = configMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param config 实例对象
     * @return 实例对象
     */
    public Config insert(Config config){
        configMapper.insert(config);
        return config;
    }
    
    /** 
     * 更新数据
     *
     * @param config 实例对象
     * @return 实例对象
     */
    public Config update(Config config){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Config> chainWrapper = new LambdaUpdateChainWrapper<Config>(configMapper);
        if(StrUtil.isNotBlank(config.getTenantId())){
            chainWrapper.eq(Config::getTenantId, config.getTenantId());
        }
        if(StrUtil.isNotBlank(config.getRevision())){
            chainWrapper.eq(Config::getRevision, config.getRevision());
        }
        if(StrUtil.isNotBlank(config.getCreatedBy())){
            chainWrapper.eq(Config::getCreatedBy, config.getCreatedBy());
        }
        if(StrUtil.isNotBlank(config.getUpdatedBy())){
            chainWrapper.eq(Config::getUpdatedBy, config.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(config.getName())){
            chainWrapper.eq(Config::getName, config.getName());
        }
        if(StrUtil.isNotBlank(config.getDetail())){
            chainWrapper.eq(Config::getDetail, config.getDetail());
        }
        if(StrUtil.isNotBlank(config.getTypeCode())){
            chainWrapper.eq(Config::getTypeCode, config.getTypeCode());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Config::getId, config.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(config.getId());
        }else{
            return config;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id){
        int total = configMapper.deleteById(id);
        return total > 0;
    }
}