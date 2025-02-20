package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author 范佳兴
 * @date 2025/2/19 16:16
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_reinstatement_application")
public class ReinstatementApplication implements Serializable {

    /**
     * 复学申请ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long applicationId;

    /**
     * 学生ID，外键关联学生表
     */
    private Long userId;

    @TableField(exist = false)
    private String userName;

    /**
     * 出境学习申请ID，外键关联学生出境学习申请表
     */
    private Long outboundApplicationId;

    /**
     * 复学申请时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime reinstatementDate;

    /**
     * 复学学期
     */
    private String exchangeTerm;

    /**
     * 申请状态（0：正常，1：删除）
     */
    private Integer status;

    /**
     * 学院审核人ID
     */
    private Long collegeReviewerId;

    /**
     * 学院审核人姓名
     */
    @TableField(exist = false)
    private String collegeReviewerName;

    /**
     * 学院审核时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime collegeReviewDate;

    /**
     * 学院审核意见
     */
    private String collegeReviewComments;

    /**
     * 学院审核结果（0：待审核，1：通过，2：拒绝）
     */
    private Integer collegeReviewResult;

    /**
     * 学校审核人ID
     */
    private Long universityReviewerId;

    /**
     * 学校审核人姓名
     */
    @TableField(exist = false)
    private String universityReviewerName;

    /**
     * 学校审核时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime universityReviewDate;

    /**
     * 学校审核意见
     */
    private String universityReviewComments;

    /**
     * 学校审核结果（0：待审核，1：通过，2：拒绝）
     */
    private Integer universityReviewResult;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
