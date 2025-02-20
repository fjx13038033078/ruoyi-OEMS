package com.ruoyi.oversea.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.oversea.domain.ExchangeApplication;
import com.ruoyi.oversea.mapper.ExchangeApplicationMapper;
import com.ruoyi.oversea.service.ExchangeApplicationService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/20 8:55
 */
@Service
@RequiredArgsConstructor
public class ExchangeApplicationServiceImpl implements ExchangeApplicationService {
    private final ExchangeApplicationMapper exchangeApplicationMapper;
    private final ISysUserService iSysUserService;

    /**
     * 获取所有学分置换申请
     *
     * @return 所有学分置换申请列表
     */
    @Transactional
    @Override
    public List<ExchangeApplication> getAllExchangeApplications() {
        List<ExchangeApplication> allApplications = exchangeApplicationMapper.getAllExchangeApplications();
        fillExchangeApplicationUserName(allApplications);
        return allApplications;
    }

    /**
     * 根据学分置换申请ID获取学分置换申请信息
     *
     * @param applicationId 学分置换申请ID
     * @return 学分置换申请信息
     */
    @Override
    public ExchangeApplication getExchangeApplicationById(Long applicationId) {
        return exchangeApplicationMapper.getExchangeApplicationById(applicationId);
    }

    /**
     * 添加学分置换申请
     *
     * @param exchangeApplication 待添加的学分置换申请信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addExchangeApplication(ExchangeApplication exchangeApplication) {
        Long userId = SecurityUtils.getUserId();
        exchangeApplication.setUserId(userId);
        int rows = exchangeApplicationMapper.addExchangeApplication(exchangeApplication);
        return rows > 0;
    }

    /**
     * 更新学分置换申请信息
     *
     * @param exchangeApplication 待更新的学分置换申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateExchangeApplication(ExchangeApplication exchangeApplication) {
        int rows = exchangeApplicationMapper.updateExchangeApplication(exchangeApplication);
        return rows > 0;
    }

    /**
     * 更新学院审核结果
     *
     * @param exchangeApplication 待更新的学分置换申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateCollegeReview(ExchangeApplication exchangeApplication) {
        Long userId = SecurityUtils.getUserId();
        exchangeApplication.setCollegeReviewerId(userId);
        int rows = exchangeApplicationMapper.updateCollegeReview(exchangeApplication);
        return rows > 0;
    }

    /**
     * 更新学校审核结果
     *
     * @param exchangeApplication 待更新的学分置换申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateUniversityReview(ExchangeApplication exchangeApplication) {
        Long userId = SecurityUtils.getUserId();
        exchangeApplication.setUniversityReviewerId(userId);
        int rows = exchangeApplicationMapper.updateUniversityReview(exchangeApplication);
        return rows > 0;
    }

    /**
     * 删除学分置换申请
     *
     * @param applicationId 待删除的学分置换申请ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteExchangeApplication(Long applicationId) {
        int rows = exchangeApplicationMapper.deleteExchangeApplication(applicationId);
        return rows > 0;
    }

    /**
     * 根据学生ID获取学分置换申请
     *
     * @param userId 学生ID
     * @return 学分置换申请列表
     */
    @Override
    public List<ExchangeApplication> getExchangeApplicationsByUserId(Long userId) {
        return exchangeApplicationMapper.getExchangeApplicationsByUserId(userId);
    }

    /**
     * 填充学分置换申请的用户姓名
     *
     * @param applications 学分置换申请列表
     */
    private void fillExchangeApplicationUserName(List<ExchangeApplication> applications) {
        for (ExchangeApplication application : applications) {
            Long userId = application.getUserId();
            String userName = (userId != null) ? iSysUserService.selectUserById(userId).getNickName() : "未知";

            Long collegeReviewerId = application.getCollegeReviewerId();
            String collegeReviewerName = (collegeReviewerId != null) ? iSysUserService.selectUserById(collegeReviewerId).getNickName() : "";

            Long universityReviewerId = application.getUniversityReviewerId();
            String universityReviewerName = (universityReviewerId != null) ? iSysUserService.selectUserById(universityReviewerId).getNickName() : "";

            application.setUserName(userName);
            application.setCollegeReviewerName(collegeReviewerName);
            application.setUniversityReviewerName(universityReviewerName);
        }
    }
}
