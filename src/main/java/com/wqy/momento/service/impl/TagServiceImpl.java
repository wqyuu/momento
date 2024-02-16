package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wqy.momento.entity.Tag;
import com.wqy.momento.mapper.TagMapper;
import com.wqy.momento.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

 /**
 * 标签;(tag)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2022-6-28
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(String id){
        return tagMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param tag 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    @Override
    public Page<Tag> paginQuery(Tag tag, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(tag.getTenantId())){
            queryWrapper.eq(Tag::getTenantId, tag.getTenantId());
        }
        if(StrUtil.isNotBlank(tag.getRevision())){
            queryWrapper.eq(Tag::getRevision, tag.getRevision());
        }
        if(StrUtil.isNotBlank(tag.getCreatedBy())){
            queryWrapper.eq(Tag::getCreatedBy, tag.getCreatedBy());
        }
        if(StrUtil.isNotBlank(tag.getUpdatedBy())){
            queryWrapper.eq(Tag::getUpdatedBy, tag.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(tag.getCode())){
            queryWrapper.eq(Tag::getCode, tag.getCode());
        }
        if(StrUtil.isNotBlank(tag.getName())){
            queryWrapper.eq(Tag::getName, tag.getName());
        }
        //2. 执行分页查询
        Page<Tag> pagin = new Page<>(current , size , true);
        IPage<Tag> selectResult = tagMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag insert(Tag tag){
        tagMapper.insert(tag);
        return tag;
    }
    
    /** 
     * 更新数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag update(Tag tag){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Tag> chainWrapper = new LambdaUpdateChainWrapper<Tag>(tagMapper);
        if(StrUtil.isNotBlank(tag.getTenantId())){
            chainWrapper.eq(Tag::getTenantId, tag.getTenantId());
        }
        if(StrUtil.isNotBlank(tag.getRevision())){
            chainWrapper.eq(Tag::getRevision, tag.getRevision());
        }
        if(StrUtil.isNotBlank(tag.getCreatedBy())){
            chainWrapper.eq(Tag::getCreatedBy, tag.getCreatedBy());
        }
        if(StrUtil.isNotBlank(tag.getUpdatedBy())){
            chainWrapper.eq(Tag::getUpdatedBy, tag.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(tag.getCode())){
            chainWrapper.eq(Tag::getCode, tag.getCode());
        }
        if(StrUtil.isNotBlank(tag.getName())){
            chainWrapper.eq(Tag::getName, tag.getName());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Tag::getId, tag.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(tag.getId());
        }else{
            return tag;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id){
        int total = tagMapper.deleteById(id);
        return total > 0;
    }
}