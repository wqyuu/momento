package com.wqy.momento.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 配置;
 * @author : wqy
 * @date : 2022-6-28
 */
@Data
@ApiModel(value = "配置",description = "")
@TableName("config")
public class Config{
    /** 租户号 */
    @ApiModelProperty(name = "租户号",notes = "")
    private String tenantId ;
    /** 乐观锁 */
    @ApiModelProperty(name = "乐观锁",notes = "")
    private String revision ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    private String createdBy ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date createdTime ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    private Date updatedTime ;
    /** ID */
    @ApiModelProperty(name = "ID",notes = "")
    @TableId
    private String id ;
    /** 名称 */
    @ApiModelProperty(name = "名称",notes = "")
    private String name ;
    /** 详情 */
    @ApiModelProperty(name = "详情",notes = "")
    private String detail ;
    /** 类型编码 */
    @ApiModelProperty(name = "类型编码",notes = "")
    private String typeCode ;

}