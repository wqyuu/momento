package com.wqy.momento.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wqy.momento.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 时间点记录;
 *
 * @author : wqy
 * @date : 2022-7-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TimingRecordVO extends BaseEntity {

    /**
     * 时机名称
     */
    @ApiModelProperty(name = "时机编码", notes = "")
    private String timingCode;

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