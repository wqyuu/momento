package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 用户积分;
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Data
@ApiModel(value = "用户积分",description = "")
@TableName("user_point")
public class UserPoint  extends  BaseEntity{

    /** ID */
    @ApiModelProperty(name = "ID",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 用户编码 */
    @ApiModelProperty(name = "用户编码",notes = "")
    private String userId ;
    /** 积分 */
    @ApiModelProperty(name = "积分",notes = "")
    private BigDecimal point ;

}