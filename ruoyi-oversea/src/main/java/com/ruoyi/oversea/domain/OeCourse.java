package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author 范佳兴
 * @date 2025/3/14 15:23
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_course")
public class OeCourse {
    @TableId(type = IdType.AUTO)
    private Long courseId;       // 课程ID
    private String courseName;   // 课程名称
    private BigDecimal credit;       // 课程学分
    private Long universityId;   // 境外学校ID

    @TableField(exist = false)
    private String universityName;
}
