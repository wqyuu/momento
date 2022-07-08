package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("momento")
public class Momento  {
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
     * ID
     */
    @TableId
    private String id;
    /**
     * 内容
     */
    private byte[] content;
    /**
     * 标题
     */
    private String title;
    /**
     * 标签
     */
    private String tag;
    /**
     * 类型
     */
    private String type;
    /**
     * 来源
     */
    private String source;
}