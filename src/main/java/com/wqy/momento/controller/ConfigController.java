package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.config.MomentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.wqy.momento.entity.Config;
import com.wqy.momento.service.ConfigService;

 /**
 * 配置;(config)表控制层
 * @author : wqy
 * @date : 2022-6-28
 */
@Api(tags = "配置对象功能接口")
@RestController
@RequestMapping("/config")
public class ConfigController{
    @Autowired
    private ConfigService configService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public MomentResponse<Config> queryById(String id){
        return MomentResponse.ok(configService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param config 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public MomentResponse<PageImpl<Config>> paginQuery(Config config, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Config> pageResult = configService.paginQuery(config, current,size);
        //3. 分页结果组装
        List<Config> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Config> retPage = new PageImpl<Config>(dataList,pageRequest,total);
        return   MomentResponse.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param config 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public MomentResponse<Config> add(Config config){
        return   MomentResponse.ok(configService.insert(config));
    }
    
    /** 
     * 更新数据
     *
     * @param config 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public MomentResponse<Config> edit(Config config){
        return   MomentResponse.ok(configService.update(config));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public MomentResponse<Boolean> deleteById(String id){
        return   MomentResponse.ok(configService.deleteById(id));
    }
}