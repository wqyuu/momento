package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("momento")
public class Momento  extends  BaseEntity {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 内容
     */
    private String content;
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