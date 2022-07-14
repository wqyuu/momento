package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

 /**
 * 标签;
 * @author : wqy
 * @date : 2022-6-28
 */
 @Data
@TableName("tag")
public class Tag  extends  BaseEntity{

    /** ID */
    @TableId
    private String id ;
    /** 标签码 */
    private String code ;
    /** 标签名称 */
    private String name ;

}