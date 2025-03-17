package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author 范佳兴
 * @date 2025/3/17 10:18
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_grade")
public class OeGrade implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成绩ID，主键
     */
    @TableId(type = IdType.AUTO)
    private Long gradeId;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 学生姓名
     */
    @TableField(exist = false)
    private String studentName;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课程名称
     */
    @TableField(exist = false)
    private String courseName;

    /**
     * 成绩
     */
    private BigDecimal grade;

    /**
     * 学期
     */
    private String semester;

    /**
     * 学分
     */
    private BigDecimal credit;
}
