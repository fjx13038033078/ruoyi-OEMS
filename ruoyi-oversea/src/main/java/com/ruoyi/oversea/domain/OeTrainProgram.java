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
 * @date 2025/3/17 9:39
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_train_program")
public class OeTrainProgram implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 培养方案ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 专业ID
     */
    private Long majorId;

    /**
     * 专业名
     */
    @TableField(exist = false)
    private String majorName;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程学分
     */
    private BigDecimal credit;

    /**
     * 课程属性（0-必修，1-选修）
     */
    private Integer property;
}
