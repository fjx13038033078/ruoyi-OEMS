package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.OeInMajor;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 8:58
 */
public interface OeInMajorService {
    /**
     * 获取所有专业信息
     *
     * @return 专业列表
     */
    List<OeInMajor> getAllMajors();

    /**
     * 根据专业ID获取专业详情
     *
     * @param majorId 专业ID
     * @return 专业详情
     */
    OeInMajor getMajorById(Long majorId);

    /**
     * 添加专业
     *
     * @param major 专业信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addMajor(OeInMajor major);

    /**
     * 更新专业信息
     *
     * @param major 专业信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateMajor(OeInMajor major);

    /**
     * 删除专业
     *
     * @param majorId 专业ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteMajor(Long majorId);
}
