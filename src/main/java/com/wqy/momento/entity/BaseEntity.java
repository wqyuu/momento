package com.wqy.momento.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * @Author qywu11
 * @Date 2022/7/14 11:49
 * @Version 1.0
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {
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
    @CreatedDate
    @Column(
            name = "created_time",
            nullable = false
    )
    private Instant createdTime = Instant.now();
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @LastModifiedDate
    @Column(
            name = "updated_time"
    )
    private Instant updatedTime = Instant.now();
}
