package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.config.ResultCode;
import com.wqy.momento.util.CnCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.wqy.momento.entity.Timing;
import com.wqy.momento.mapper.TimingMapper;
import com.wqy.momento.service.TimingService;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 时机;(timing)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Slf4j
@Service
public class TimingServiceImpl implements TimingService{
    @Autowired
    private TimingMapper timingMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Timing queryById(Integer id){
        return timingMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param timing 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Timing> paginQuery(Timing timing, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Timing> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(timing.getTenantId())){
            queryWrapper.eq(Timing::getTenantId, timing.getTenantId());
        }
        if(StrUtil.isNotBlank(timing.getRevision())){
            queryWrapper.eq(Timing::getRevision, timing.getRevision());
        }
        if(StrUtil.isNotBlank(timing.getCreatedBy())){
            queryWrapper.eq(Timing::getCreatedBy, timing.getCreatedBy());
        }
        if(StrUtil.isNotBlank(timing.getUpdatedBy())){
            queryWrapper.eq(Timing::getUpdatedBy, timing.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(timing.getCode())){
            queryWrapper.eq(Timing::getCode, timing.getCode());
        }
        if(StrUtil.isNotBlank(timing.getName())){
            queryWrapper.eq(Timing::getName, timing.getName());
        }

        if(StrUtil.isNotBlank(timing.getType())){
            queryWrapper.eq(Timing::getType, timing.getType());
        }
        //2. 执行分页查询
        Page<Timing> pagin = new Page<>(current , size , true);
        IPage<Timing> selectResult = timingMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param timing 实例对象
     * @return 实例对象
     */
    public MomentResponse insert(Timing timing){
        if(isExist(timing)){
            log.error("timing[{}] is exist",timing.getName());
            return MomentResponse.failure(ResultCode.DATA_ALREADY_EXISTED);
        }
        timing.setCode(CnCodeUtil.code(timing.getName()));
        timingMapper.insert(timing);
        return MomentResponse.ok(timing);
    }

    private boolean isExist(Timing timing){
         Map<String,Object> query = new HashMap<>();
         query.put("name",timing.getName());
         query.put("created_by",timing.getCreatedBy());
         List<Timing> timings = timingMapper.selectByMap(query);
         return !CollectionUtils.isEmpty(timings);
    }
    
    /** 
     * 更新数据
     *
     * @param timing 实例对象
     * @return 实例对象
     */
    public Timing update(Timing timing){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Timing> chainWrapper = new LambdaUpdateChainWrapper<Timing>(timingMapper);
        if(StrUtil.isNotBlank(timing.getTenantId())){
            chainWrapper.eq(Timing::getTenantId, timing.getTenantId());
        }
        if(StrUtil.isNotBlank(timing.getRevision())){
            chainWrapper.eq(Timing::getRevision, timing.getRevision());
        }
        if(StrUtil.isNotBlank(timing.getCreatedBy())){
            chainWrapper.eq(Timing::getCreatedBy, timing.getCreatedBy());
        }
        if(StrUtil.isNotBlank(timing.getUpdatedBy())){
            chainWrapper.eq(Timing::getUpdatedBy, timing.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(timing.getCode())){
            chainWrapper.eq(Timing::getCode, timing.getCode());
        }
        if(StrUtil.isNotBlank(timing.getName())){
            chainWrapper.eq(Timing::getName, timing.getName());
        }
        if(StrUtil.isNotBlank(timing.getType())){
            chainWrapper.eq(Timing::getType, timing.getType());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Timing::getId, timing.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(timing.getId());
        }else{
            return timing;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        int total = timingMapper.deleteById(id);
        return total > 0;
    }
}