package com.wqy.momento.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wqy.momento.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
 * 类别;(type)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2022-6-28
 */
@Mapper
public interface TypeMapper  extends BaseMapper<Type>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<Type> selectByPage(IPage<Type> page ,@Param(Constants.WRAPPER) Wrapper<Type> wrapper );
}