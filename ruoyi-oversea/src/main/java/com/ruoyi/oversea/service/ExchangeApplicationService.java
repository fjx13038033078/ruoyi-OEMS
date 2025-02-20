package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.ExchangeApplication;

import java.util.List;

/**
* @Author 范佳兴
* @date 2025/2/20 8:54
*/public interface ExchangeApplicationService {
    /**
     * 获取所有学分置换申请
     *
     * @return 所有学分置换申请列表
     */
    List<ExchangeApplication> getAllExchangeApplications();

    /**
     * 根据学分置换申请ID获取学分置换申请信息
     *
     * @param applicationId 学分置换申请ID
     * @return 学分置换申请信息
     */
    ExchangeApplication getExchangeApplicationById(Long applicationId);

    /**
     * 添加学分置换申请
     *
     * @param exchangeApplication 待添加的学分置换申请信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addExchangeApplication(ExchangeApplication exchangeApplication);

    /**
     * 更新学分置换申请信息
     *
     * @param exchangeApplication 待更新的学分置换申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateExchangeApplication(ExchangeApplication exchangeApplication);

    /**
     * 更新学院审核结果
     *
     * @param exchangeApplication 待更新的学分置换申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateCollegeReview(ExchangeApplication exchangeApplication);

    /**
     * 更新学校审核结果
     *
     * @param exchangeApplication 待更新的学分置换申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateUniversityReview(ExchangeApplication exchangeApplication);

    /**
     * 删除学分置换申请
     *
     * @param applicationId 待删除的学分置换申请ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteExchangeApplication(Long applicationId);

    /**
     * 根据学生ID获取学分置换申请
     *
     * @param userId 学生ID
     * @return 学分置换申请列表
     */
    List<ExchangeApplication> getExchangeApplicationsByUserId(Long userId);
}
