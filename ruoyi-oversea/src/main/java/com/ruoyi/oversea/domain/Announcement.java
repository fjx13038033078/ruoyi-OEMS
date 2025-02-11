package com.ruoyi.oversea.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:19
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("oe_announcement")
public class Announcement implements Serializable {
    /**
     * 公告ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long announcementId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布时间
     */
    private LocalDateTime releaseDate;

    /**
     * 结束时间
     */
    private LocalDateTime endDate;

    /**
     * 发布人ID（学院管理员或学校管理员）
     */
    private Long authorId;

    /**
     * 境外学校ID
     */
    private Long oeUniversityId;

    /**
     * 附件文件名
     */
    private String fileName;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
