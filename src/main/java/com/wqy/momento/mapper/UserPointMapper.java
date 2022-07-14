package com.wqy.momento.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wqy.momento.entity.UserPoint;

 /**
 * 用户积分;(user_point)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Mapper
public interface UserPointMapper  extends BaseMapper<UserPoint>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<UserPoint> selectByPage(IPage<UserPoint> page , @Param(Constants.WRAPPER) Wrapper<UserPoint> wrapper);
}