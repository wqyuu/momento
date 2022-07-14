package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.entity.Tag;
import com.wqy.momento.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

 /**
 * 标签;(tag)表控制层
 * @author : wqy
 * @date : 2022-6-28
 */
@RestController
@RequestMapping("/tag")
public class TagController{
    @Autowired
    private TagService tagService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping("{id}")
    public MomentResponse<Tag> queryById(String id){
        return  MomentResponse.ok(tagService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param tag 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public MomentResponse<PageImpl<Tag>> paginQuery(Tag tag, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Tag> pageResult = tagService.paginQuery(tag, current,size);
        //3. 分页结果组装
        List<Tag> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Tag> retPage = new PageImpl<Tag>(dataList,pageRequest,total);
        return  MomentResponse.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @PostMapping
    public MomentResponse<Tag> add(Tag tag){
        return  MomentResponse.ok(tagService.insert(tag));
    }
    
    /** 
     * 更新数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @PutMapping
    public MomentResponse<Tag> edit(Tag tag){
        return  MomentResponse.ok(tagService.update(tag));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping
    public MomentResponse<Boolean> deleteById(String id){
        return  MomentResponse.ok(tagService.deleteById(id));
    }
}