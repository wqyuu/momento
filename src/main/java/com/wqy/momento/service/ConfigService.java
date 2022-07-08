package com.wqy.momento.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.entity.Config;

 /**
 * 配置;(config)表服务接口
 * @author : wqy
 * @date : 2022-6-28
 */
public interface ConfigService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Config queryById(String id);
    
    /**
     * 分页查询
     *
     * @param config 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Config> paginQuery(Config config, long current, long size);
    /** 
     * 新增数据
     *
     * @param config 实例对象
     * @return 实例对象
     */
    Config insert(Config config);
    /** 
     * 更新数据
     *
     * @param config 实例对象
     * @return 实例对象
     */
    Config update(Config config);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
}