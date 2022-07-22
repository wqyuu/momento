package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.config.MomentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.wqy.momento.entity.UserPoint;
import com.wqy.momento.service.UserPointService;

 /**
 * 用户积分;(user_point)表控制层
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Api(tags = "用户积分对象功能接口")
@RestController
@RequestMapping("/point")
public class UserPointController{
    @Autowired
    private UserPointService userPointService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public MomentResponse<UserPoint> queryById(Integer id){
        return  MomentResponse.ok(userPointService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param userPoint 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public MomentResponse<PageImpl<UserPoint>> paginQuery(UserPoint userPoint, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserPoint> pageResult = userPointService.paginQuery(userPoint, current,size);
        //3. 分页结果组装
        List<UserPoint> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<UserPoint> retPage = new PageImpl<UserPoint>(dataList,pageRequest,total);
        return  MomentResponse.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param userPoint 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public MomentResponse<UserPoint> add(UserPoint userPoint){
        return  MomentResponse.ok(userPointService.insert(userPoint));
    }
    
    /** 
     * 更新数据
     *
     * @param userPoint 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public MomentResponse<UserPoint> edit(UserPoint userPoint){
        return  MomentResponse.ok(userPointService.update(userPoint));
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
        return  MomentResponse.ok(userPointService.deleteById(id));
    }

    // 查询我的积分
    @GetMapping("/user/{id}")
    public MomentResponse<UserPoint> queryByUserId(String id){
        return  MomentResponse.ok(userPointService.selectByUser(id));
    }
}