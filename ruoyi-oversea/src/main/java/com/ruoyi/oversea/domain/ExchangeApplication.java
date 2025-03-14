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
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author 范佳兴
 * @date 2025/2/19 17:35
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_exchange_application")
public class ExchangeApplication implements Serializable {
    /**
     * 学分置换申请ID，主键，自增
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
     * 复学申请ID，外键关联学生复学申请表
     */
    private Long reinstatementApplicationId;

    /**
     * 学分置换申请日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime applicationDate;

    /**
     * 申请状态（0：待审核，1：已通过，2：已拒绝）
     */
    private Integer status;

    /**
     * 学院审核人ID
     */
    private Long collegeReviewerId;

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
     * 境外课程ID
     */
    private Long overseasCourseId;

    @TableField(exist = false)
    private String overseasCourseName;

    /**
     * 境外课程学分
     */
    private BigDecimal overseasCourseCredits;

    /**
     * 校内课程名
     */
    private String domesticCourseName;

    /**
     * 校内课程学分
     */
    private BigDecimal domesticCourseCredits;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
