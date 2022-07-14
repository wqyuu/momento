package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.entity.TimingRecord;
import com.wqy.momento.entity.vo.TimingRecordVO;

/**
 * 时间点记录;(timing_record)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
public interface TimingRecordService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    TimingRecord queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param timingRecord 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<TimingRecord> paginQuery(TimingRecord timingRecord, long current, long size);
    /** 
     * 新增数据
     *
     * @param timingRecord 实例对象
     * @return 实例对象
     */
    TimingRecord insert(TimingRecord timingRecord);
    /** 
     * 更新数据
     *
     * @param timingRecord 实例对象
     * @return 实例对象
     */
    TimingRecord update(TimingRecord timingRecord);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

     /**
      * 新增记录
      *
      * @param timingRecord 实例对象
      * @return 实例对象
      */
     MomentResponse add(TimingRecordVO timingRecordVO);
}