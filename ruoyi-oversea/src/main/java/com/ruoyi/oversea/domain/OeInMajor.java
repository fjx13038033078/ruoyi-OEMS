package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author 范佳兴
 * @date 2025/3/17 8:51
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_in_major")
public class OeInMajor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 专业ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 专业名
     */
    private String majorName;
}
