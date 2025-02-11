package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:34
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_university")
public class University implements Serializable {
    /**
     * 境外学校ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long universityId;

    /**
     * 境外学校名称
     */
    private String universityName;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
