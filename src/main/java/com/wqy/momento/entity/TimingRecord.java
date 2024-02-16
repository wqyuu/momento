package com.wqy.momento.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间点记录;
 *
 * @author : wqy
 * @date : 2022-7-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "时间点记录", description = "")
@TableName("timing_record")
public class TimingRecord extends BaseEntity {

    /**
     * ID
     */
    @ApiModelProperty(name = "ID", notes = "")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 时机名称
     */
    @ApiModelProperty(name = "时机编码", notes = "")
    private String timingCode;
    /**
     * 积分变动
     */
    @ApiModelProperty(name = "积分变动", notes = "")
    private BigDecimal point;
    /**
     * 积分变动
     */
    @ApiModelProperty(name = "积分变动", notes = "")
    private BigDecimal pointChange;
    /**
     * 程度
     */
    @ApiModelProperty(name = "程度", notes = "")
    private String degree;
    /**
     * 完成时间
     */
    @ApiModelProperty(name = "完成时间", notes = "")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;
    /**
     * 类型，0:努力 1:奖励
     */
    @ApiModelProperty(name = "类型，0:努力 1:奖励", notes = "")
    private String type;

}