package com.wqy.momento.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wqy.momento.entity.Config;

 /**
 * 配置;(config)表数据库访问层
 * @author : wqy
 * @date : 2022-6-28
 */
@Mapper
public interface ConfigMapper  extends BaseMapper<Config>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<Config> selectByPage(IPage<Config> page , @Param(Constants.WRAPPER) Wrapper<Config> wrapper);
}