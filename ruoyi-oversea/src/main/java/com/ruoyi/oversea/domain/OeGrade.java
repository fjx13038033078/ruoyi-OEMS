package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(type = IdType.INPUT) // 由于DDL中未设置自增，这里设为手动输入
    private Long gradeId;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 课程ID
     */
    private Long courseId;

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
