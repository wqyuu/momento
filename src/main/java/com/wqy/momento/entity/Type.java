package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author qywu11
 */
@Data
@TableName("type")
public class Type  extends  BaseEntity{

    /**
     * id
     */
    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
}