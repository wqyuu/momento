package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wqy.momento.entity.Momento;
import com.wqy.momento.entity.Tag;
import com.wqy.momento.entity.Type;
import com.wqy.momento.mapper.MomentoMapper;
import com.wqy.momento.mapper.TagMapper;
import com.wqy.momento.mapper.TypeMapper;
import com.wqy.momento.service.MomentoService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * momento;(momento)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2022-6-28
 */
@Service
public class MomentoServiceImpl implements MomentoService {
    @Autowired
    private MomentoMapper momentoMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TypeMapper typeMapper;

     /**
      * 通过ID查询单条数据
      *
      * @param id 主键
      * @return 实例对象
      */
    @Override
    public Momento queryById(Integer id){
        return momentoMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param momento 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    @Override
    public Page<Momento> paginQuery(Momento momento, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Momento> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(momento.getTenantId())){
            queryWrapper.eq(Momento::getTenantId, momento.getTenantId());
        }
        if(StrUtil.isNotBlank(momento.getRevision())){
            queryWrapper.eq(Momento::getRevision, momento.getRevision());
        }
        if(StrUtil.isNotBlank(momento.getCreatedBy())){
            queryWrapper.eq(Momento::getCreatedBy, momento.getCreatedBy());
        }
        if(StrUtil.isNotBlank(momento.getUpdatedBy())){
            queryWrapper.eq(Momento::getUpdatedBy, momento.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(momento.getTitle())){
            queryWrapper.eq(Momento::getTitle, momento.getTitle());
        }
        if(StrUtil.isNotBlank(momento.getTag())){
            queryWrapper.eq(Momento::getTag, momento.getTag());
        }
        if(StrUtil.isNotBlank(momento.getType())){
            queryWrapper.eq(Momento::getType, momento.getType());
        }
        if(StrUtil.isNotBlank(momento.getSource())){
            queryWrapper.eq(Momento::getSource, momento.getSource());
        }
        //2. 执行分页查询
        Page<Momento> pagin = new Page<>(current , size , true);
        IPage<Momento> selectResult = momentoMapper.selectByPage(pagin , queryWrapper);

//        selectResult.getRecords().stream().map(o->this.form(o));
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param momento 实例对象
     * @return 实例对象
     */
    @Override
    public Momento insert(Momento momento){
        momentoMapper.insert(momento);
        return momento;
    }
    
    /** 
     * 更新数据
     *
     * @param momento 实例对象
     * @return 实例对象
     */
    @Override
    public Momento update(Momento momento){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Momento> chainWrapper = new LambdaUpdateChainWrapper<Momento>(momentoMapper);
        if(StrUtil.isNotBlank(momento.getTenantId())){
            chainWrapper.eq(Momento::getTenantId, momento.getTenantId());
        }
        if(StrUtil.isNotBlank(momento.getRevision())){
            chainWrapper.eq(Momento::getRevision, momento.getRevision());
        }
        if(StrUtil.isNotBlank(momento.getCreatedBy())){
            chainWrapper.eq(Momento::getCreatedBy, momento.getCreatedBy());
        }
        if(StrUtil.isNotBlank(momento.getUpdatedBy())){
            chainWrapper.eq(Momento::getUpdatedBy, momento.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(momento.getTitle())){
            chainWrapper.eq(Momento::getTitle, momento.getTitle());
        }
        if(StrUtil.isNotBlank(momento.getTag())){
            chainWrapper.eq(Momento::getTag, momento.getTag());
        }
        if(StrUtil.isNotBlank(momento.getType())){
            chainWrapper.eq(Momento::getType, momento.getType());
        }
        if(StrUtil.isNotBlank(momento.getSource())){
            chainWrapper.eq(Momento::getSource, momento.getSource());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Momento::getId, momento.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(momento.getId());
        }else{
            return momento;
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
        int total = momentoMapper.deleteById(id);
        return total > 0;
    }

     private void form(Momento entity){

         Map<String, Object> tagColumnMap = new HashMap<>();
         tagColumnMap.put("code",entity.getTag());
         List<Tag> tagList = tagMapper.selectByMap(tagColumnMap);
         tagList.stream().findFirst().ifPresent(t ->{
             entity.setTag(t.getName());
         });

         Map<String, Object> typeColumnMap = new HashMap<>();
         typeColumnMap.put("code",entity.getTag());
         List<Type> typeList = typeMapper.selectByMap(typeColumnMap);
         typeList.stream().findFirst().ifPresent(t ->{
             entity.setType(t.getName());
         });

     }
}