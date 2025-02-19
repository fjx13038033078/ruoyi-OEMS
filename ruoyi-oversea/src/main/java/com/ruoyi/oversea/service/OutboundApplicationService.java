package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.OutboundApplication;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/18 10:23
 */
public interface OutboundApplicationService {
    /**
     * 获取所有出境申请
     *
     * @return 所有出境申请列表
     */
    List<OutboundApplication> getAllOutboundApplications();

    /**
     * 根据申请ID获取出境申请信息
     *
     * @param applicationId 申请ID
     * @return 出境申请信息
     */
    OutboundApplication getOutboundApplicationById(Long applicationId);

    /**
     * 添加出境申请
     *
     * @param outboundApplication 待添加的出境申请信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addOutboundApplication(OutboundApplication outboundApplication);

    /**
     * 更新出境申请信息
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateOutboundApplication(OutboundApplication outboundApplication);

    /**
     * 更新学院审核结果
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateCollegeReview(OutboundApplication outboundApplication);

    /**
     * 更新学校审核结果
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateUniversityReview(OutboundApplication outboundApplication);

    /**
     * 删除出境申请
     *
     * @param applicationId 待删除的出境申请ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteOutboundApplication(Long applicationId);

    /**
     * 根据学生ID获取出境申请
     *
     * @param userId 学生ID
     * @return 出境申请列表
     */
    List<OutboundApplication> getOutboundApplicationsByUserId(Long userId);

    /**
     * 获取特定审核状态的出境申请列表
     *
     * @param collegeReviewResult 学院审核结果
     * @param universityReviewResult 学校审核结果
     * @return 出境申请列表
     */
    List<OutboundApplication> getOutboundApplicationsByReviewStatus(Integer collegeReviewResult, Integer universityReviewResult);
}
