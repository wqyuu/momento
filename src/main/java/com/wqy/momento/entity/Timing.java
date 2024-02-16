package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 /**
 * 时机;
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Data
@ApiModel(value = "时机",description = "")
@TableName("timing")
public class Timing extends  BaseEntity{

    /** ID */
    @ApiModelProperty(name = "ID",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 唯一码 */
    @ApiModelProperty(name = "唯一码",notes = "")
    private String code ;
    /** 名称 */
    @ApiModelProperty(name = "名称",notes = "")
    private String name ;
    /** 积分 */
    @ApiModelProperty(name = "积分",notes = "")
    private BigDecimal point ;
    /** 类型，0:努力 1:奖励 */
    @ApiModelProperty(name = "类型，0:努力 1:奖励",notes = "")
    private String type ;

}