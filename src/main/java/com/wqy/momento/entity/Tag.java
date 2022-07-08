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
public class Tag{
    /** 租户号 */
    private String tenantId ;
    /** 乐观锁 */
    private String revision ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;
    /** ID */
    @TableId
    private String id ;
    /** 标签码 */
    private String code ;
    /** 标签名称 */
    private String name ;

}