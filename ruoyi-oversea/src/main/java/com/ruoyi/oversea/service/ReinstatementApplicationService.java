package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.ReinstatementApplication;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/19 16:23
 */
public interface ReinstatementApplicationService {
    /**
     * 获取所有复学申请
     *
     * @return 所有复学申请列表
     */
    List<ReinstatementApplication> getAllReinstatementApplications();

    /**
     * 根据申请ID获取复学申请信息
     *
     * @param applicationId 复学申请ID
     * @return 复学申请信息
     */
    ReinstatementApplication getReinstatementApplicationById(Long applicationId);

    /**
     * 添加复学申请
     *
     * @param reinstatementApplication 待添加的复学申请信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addReinstatementApplication(ReinstatementApplication reinstatementApplication);

    /**
     * 更新复学申请信息
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateReinstatementApplication(ReinstatementApplication reinstatementApplication);

    /**
     * 更新学院审核结果
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateCollegeReview(ReinstatementApplication reinstatementApplication);

    /**
     * 更新学校审核结果
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateUniversityReview(ReinstatementApplication reinstatementApplication);

    /**
     * 删除复学申请
     *
     * @param applicationId 待删除的复学申请ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteReinstatementApplication(Long applicationId);

    /**
     * 根据学生ID获取复学申请
     *
     * @param userId 学生ID
     * @return 复学申请列表
     */
    List<ReinstatementApplication> getReinstatementApplicationsByUserId(Long userId);

    /**
     * 获取特定审核状态的复学申请列表
     *
     * @param collegeReviewResult 学院审核结果
     * @param universityReviewResult 学校审核结果
     * @return 复学申请列表
     */
    List<ReinstatementApplication> getReinstatementApplicationsByReviewStatus(Integer collegeReviewResult, Integer universityReviewResult);
}
