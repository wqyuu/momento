package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.entity.Momento;
import com.wqy.momento.service.MomentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

 /**
 * momento;(momento)表控制层
 * @author : wqy
 * @date : 2022-6-28
 */
@RestController
@RequestMapping("/momento")
public class MomentoController{
    @Autowired
    private MomentoService momentoService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping("{id}")
    public MomentResponse<Momento> queryById(Integer id){
        return  MomentResponse.ok(momentoService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param momento 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public MomentResponse<PageImpl<Momento>> paginQuery(Momento momento,
                                                        @PageableDefault(sort = "createdTime", direction = Sort.Direction.ASC) Pageable pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Momento> pageResult = momentoService.paginQuery(momento, current,size);
        //3. 分页结果组装
        List<Momento> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Momento> retPage = new PageImpl<Momento>(dataList,pageRequest,total);
        return  MomentResponse.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param momento 实例对象
     * @return 实例对象
     */
    @PostMapping
    public MomentResponse<Momento> add(@RequestBody  Momento momento){
        return  MomentResponse.ok(momentoService.insert(momento));
    }
    
    /** 
     * 更新数据
     *
     * @param momento 实例对象
     * @return 实例对象
     */
    @PutMapping
    public MomentResponse<Momento> edit(@RequestBody Momento momento){
        return  MomentResponse.ok(momentoService.update(momento));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping
    public MomentResponse<Boolean> deleteById(String id){
        return  MomentResponse.ok(momentoService.deleteById(id));
    }
}