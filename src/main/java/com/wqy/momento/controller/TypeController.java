package com.wqy.momento.controller;

import java.util.List;

import com.wqy.momento.entity.Type;
import com.wqy.momento.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 类别;(type)表控制层
 * @author : wqy
 * @date : 2022-6-28
 */
@RestController
@RequestMapping("/type")
public class TypeController{
    @Autowired
    private TypeService typeService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping("{id}")
    public ResponseEntity<Type> queryById(String id){
        return ResponseEntity.ok(typeService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param type 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<PageImpl<Type>> paginQuery(Type type, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Type> pageResult = typeService.paginQuery(type, current,size);
        //3. 分页结果组装
        List<Type> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Type> retPage = new PageImpl<Type>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @PostMapping
    public ResponseEntity<Type> add(Type type){
        return ResponseEntity.ok(typeService.insert(type));
    }
    
    /** 
     * 更新数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @PutMapping
    public ResponseEntity<Type> edit(Type type){
        return ResponseEntity.ok(typeService.update(type));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param undefinedId 主键
     * @return 是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String undefinedId){
        return ResponseEntity.ok(typeService.deleteById(undefinedId));
    }
}