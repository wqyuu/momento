package com.wqy.momento.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wqy.momento.entity.vo.TimingRecordResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wqy.momento.entity.TimingRecord;

 /**
 * 时间点记录;(timing_record)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Mapper
public interface TimingRecordMapper  extends BaseMapper<TimingRecord>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<TimingRecord> selectByPage(IPage<TimingRecord> page , @Param(Constants.WRAPPER) Wrapper<TimingRecord> wrapper);

    IPage<TimingRecordResp> selectUser(IPage<TimingRecord> page, TimingRecord record);
}