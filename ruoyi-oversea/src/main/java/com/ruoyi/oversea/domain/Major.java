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

/**
 * @Author 范佳兴
 * @date 2025/3/14 14:29
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_major")
public class Major implements Serializable {

    /**
     * 专业ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long majorId;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 境外学校ID
     */
    private Long universityId;

    /**
     * 境外学校名称（非数据库字段）
     */
    @TableField(exist = false)
    private String universityName;

    private static final long serialVersionUID = 1L;
}
