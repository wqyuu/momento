package com.wqy.momento.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.momento.MomentoApplication;
import com.wqy.momento.entity.Config;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author qywu11
 * @Date 2022/7/8 15:51
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MomentoApplication.class)
class ConfigMapperTest {

    @Autowired
    private ConfigMapper configMapper;

    @Test
    void selectByPage() {
        Config config = new Config();
        config.setCreatedBy("wqy");
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(config.getTenantId())){
            queryWrapper.eq(Config::getTenantId, config.getTenantId());
        }
        if(StrUtil.isNotBlank(config.getRevision())){
            queryWrapper.eq(Config::getRevision, config.getRevision());
        }
        if(StrUtil.isNotBlank(config.getCreatedBy())){
            queryWrapper.eq(Config::getCreatedBy, config.getCreatedBy());
        }
        if(StrUtil.isNotBlank(config.getUpdatedBy())){
            queryWrapper.eq(Config::getUpdatedBy, config.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(config.getName())){
            queryWrapper.eq(Config::getName, config.getName());
        }
        if(StrUtil.isNotBlank(config.getDetail())){
            queryWrapper.eq(Config::getDetail, config.getDetail());
        }
        if(StrUtil.isNotBlank(config.getTypeCode())){
            queryWrapper.eq(Config::getTypeCode, config.getTypeCode());
        }
        //2. 执行分页查询
        Page<Config> pagin = new Page<>(0 , 5 , true);
        IPage<Config> selectResult = configMapper.selectByPage(pagin , queryWrapper);
        System.out.println(selectResult.getRecords().toString());
    }

    @Test
    void select(){
        Config config = configMapper.selectById(1);
        System.out.println(config);
    }
}