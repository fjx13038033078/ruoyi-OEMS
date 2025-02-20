package com.ruoyi.oversea.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.oversea.domain.ReinstatementApplication;
import com.ruoyi.oversea.mapper.ReinstatementApplicationMapper;
import com.ruoyi.oversea.service.ReinstatementApplicationService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/19 16:24
 */
@Service
@RequiredArgsConstructor
public class ReinstatementApplicationServiceImpl implements ReinstatementApplicationService {

    private final ReinstatementApplicationMapper reinstatementApplicationMapper;

    private final ISysUserService iSysUserService;

    /**
     * 获取所有复学申请
     *
     * @return 所有复学申请列表
     */
    @Transactional
    @Override
    public List<ReinstatementApplication> getAllReinstatementApplications() {
        List<ReinstatementApplication> allApplications = reinstatementApplicationMapper.getAllReinstatementApplications();
        fillReinstatementApplicationUserName(allApplications);
        return allApplications;
    }

    /**
     * 根据申请ID获取复学申请信息
     *
     * @param applicationId 复学申请ID
     * @return 复学申请信息
     */
    @Override
    public ReinstatementApplication getReinstatementApplicationById(Long applicationId) {
        return reinstatementApplicationMapper.getReinstatementApplicationById(applicationId);
    }

    /**
     * 添加复学申请
     *
     * @param reinstatementApplication 待添加的复学申请信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addReinstatementApplication(ReinstatementApplication reinstatementApplication) {
        Long userId = SecurityUtils.getUserId();
        reinstatementApplication.setUserId(userId);
        int rows = reinstatementApplicationMapper.addReinstatementApplication(reinstatementApplication);
        return rows > 0;
    }

    /**
     * 更新复学申请信息
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateReinstatementApplication(ReinstatementApplication reinstatementApplication) {
        int rows = reinstatementApplicationMapper.updateReinstatementApplication(reinstatementApplication);
        return rows > 0;
    }

    /**
     * 更新学院审核结果
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateCollegeReview(ReinstatementApplication reinstatementApplication) {
        Long userId = SecurityUtils.getUserId();
        reinstatementApplication.setCollegeReviewerId(userId);
        int rows = reinstatementApplicationMapper.updateCollegeReview(reinstatementApplication);
        return rows > 0;
    }

    /**
     * 更新学校审核结果
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateUniversityReview(ReinstatementApplication reinstatementApplication) {
        Long userId = SecurityUtils.getUserId();
        reinstatementApplication.setUniversityReviewerId(userId);
        int rows = reinstatementApplicationMapper.updateUniversityReview(reinstatementApplication);
        return rows > 0;
    }

    /**
     * 删除复学申请
     *
     * @param applicationId 待删除的复学申请ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteReinstatementApplication(Long applicationId) {
        int rows = reinstatementApplicationMapper.deleteReinstatementApplication(applicationId);
        return rows > 0;
    }

    /**
     * 根据学生ID获取复学申请
     *
     * @param userId 学生ID
     * @return 复学申请列表
     */
    @Override
    public List<ReinstatementApplication> getReinstatementApplicationsByUserId(Long userId) {
        return reinstatementApplicationMapper.getReinstatementApplicationsByUserId(userId);
    }

    /**
     * 获取特定审核状态的复学申请列表
     *
     * @param collegeReviewResult 学院审核结果
     * @param universityReviewResult 学校审核结果
     * @return 复学申请列表
     */
    @Override
    public List<ReinstatementApplication> getReinstatementApplicationsByReviewStatus(Integer collegeReviewResult, Integer universityReviewResult) {
        return reinstatementApplicationMapper.getReinstatementApplicationsByReviewStatus(collegeReviewResult, universityReviewResult);
    }

    /**
     * 填充相关用户姓名
     *
     * @param applications 复学申请列表
     */
    private void fillReinstatementApplicationUserName(List<ReinstatementApplication> applications) {
        for (ReinstatementApplication application : applications) {
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
