package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.config.MomentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.wqy.momento.entity.Timing;
import com.wqy.momento.service.TimingService;

 /**
 * 时机;(timing)表控制层
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Api(tags = "时机对象功能接口")
@RestController
@RequestMapping("/timing")
public class TimingController{
    @Autowired
    private TimingService timingService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public MomentResponse<Timing> queryById(Integer id){
        return  MomentResponse.ok(timingService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param timing 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public MomentResponse<PageImpl<Timing>> paginQuery(Timing timing, Pageable pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Timing> pageResult = timingService.paginQuery(timing, current,size);
        //3. 分页结果组装
        List<Timing> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Timing> retPage = new PageImpl<Timing>(dataList,pageRequest,total);
        return  MomentResponse.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param timing 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public MomentResponse<Timing> add(@RequestBody  Timing timing){
        return  timingService.insert(timing);
    }
    
    /** 
     * 更新数据
     *
     * @param timing 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public MomentResponse<Timing> edit(Timing timing){
        return  MomentResponse.ok(timingService.update(timing));
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
        return  MomentResponse.ok(timingService.deleteById(id));
    }
}