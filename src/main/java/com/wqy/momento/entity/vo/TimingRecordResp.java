package com.wqy.momento.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author qywu11
 * @Date 2022/7/14 18:21
 * @Version 1.0
 */
@Data
public class TimingRecordResp {


    /** 名称 */
    @ApiModelProperty(name = "名称",notes = "")
    private String name ;
    /** 积分 */
    @ApiModelProperty(name = "积分",notes = "")
    private BigDecimal point ;
    /** 类型，0:努力 1:奖励 */
    @ApiModelProperty(name = "类型，0:努力 1:奖励",notes = "")
    private String type ;
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
}
