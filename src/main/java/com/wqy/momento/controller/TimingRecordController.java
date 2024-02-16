package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.entity.vo.TimingRecordResp;
import com.wqy.momento.entity.vo.TimingRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.wqy.momento.entity.TimingRecord;
import com.wqy.momento.service.TimingRecordService;

 /**
 * 时间点记录;(timing_record)表控制层
 * @author : wqy
 * @date : 2022-7-14
 */
@Api(tags = "时间点记录对象功能接口")
@RestController
@RequestMapping("/timing-record")
public class TimingRecordController{
    @Autowired
    private TimingRecordService timingRecordService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public MomentResponse<TimingRecord> queryById(Integer id){
        return  MomentResponse.ok(timingRecordService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param timingRecord 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public MomentResponse<PageImpl<TimingRecord>> paginQuery(TimingRecord timingRecord,
                                                             @PageableDefault(sort = "finishTime", direction = Sort.Direction.DESC)    Pageable pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TimingRecord> pageResult = timingRecordService.paginQuery(timingRecord, current,size);
        //3. 分页结果组装
        List<TimingRecord> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<TimingRecord> retPage = new PageImpl<TimingRecord>(dataList,pageRequest,total);
        return  MomentResponse.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param timingRecord 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public MomentResponse<TimingRecord> add(TimingRecord timingRecord){
        return  MomentResponse.ok(timingRecordService.insert(timingRecord));
    }
    
    /** 
     * 更新数据
     *
     * @param timingRecord 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public MomentResponse<TimingRecord> edit(TimingRecord timingRecord){
        return  MomentResponse.ok(timingRecordService.update(timingRecord));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public MomentResponse<Boolean> deleteById(Integer id){
        return  MomentResponse.ok(timingRecordService.deleteById(id));
    }

     /**
      * 新增时间点记录，积分变动
      *
      * @param timingRecordVO 实例对象
      * @return 实例对象
      */
     @ApiOperation("新增时间点记录")
     @PostMapping("/add")
     public MomentResponse addRecord(@RequestBody TimingRecordVO timingRecordVO){
         return  timingRecordService.add(timingRecordVO);
     }

     /**
      * 记录分页查询
      *
      * @param timingRecord 筛选条件
      * @param pageRequest 分页对象
      * @return 查询结果
      */
     @ApiOperation("分页查询")
     @GetMapping("/list")
     public MomentResponse<PageImpl<TimingRecordResp>> recordPaginQuery(TimingRecord timingRecord,
                                                                  @PageableDefault(sort = "finishTime", direction = Sort.Direction.DESC)    Pageable pageRequest){
         //1.分页参数
         long current = pageRequest.getPageNumber();
         long size = pageRequest.getPageSize();
         //2.分页查询
         /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
         com.baomidou.mybatisplus.extension.plugins.pagination.Page<TimingRecordResp> pageResult = timingRecordService.paginQueryByUser(timingRecord, current,size);
         //3. 分页结果组装
         List<TimingRecordResp> dataList = pageResult.getRecords();
         long total = pageResult.getTotal();
         PageImpl<TimingRecordResp> retPage = new PageImpl<>(dataList,pageRequest,total);
         return  MomentResponse.ok(retPage);
     }
}