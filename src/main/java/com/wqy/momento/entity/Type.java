package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author qywu11
 */
@Data
@TableName("type")
public class Type{
    /**
     * 租户号
     */
    private String tenantId;
    /**
     * 乐观锁
     */
    private String revision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
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