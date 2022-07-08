package com.wqy.momento.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wqy.momento.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
 * 标签;(tag)表数据库访问层
 * @author : wqy
 * @date : 2022-6-28
 */
@Mapper
public interface TagMapper  extends BaseMapper<Tag>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<Tag> selectByPage(IPage<Tag> page , @Param(Constants.WRAPPER) Wrapper<Tag> wrapper);
}