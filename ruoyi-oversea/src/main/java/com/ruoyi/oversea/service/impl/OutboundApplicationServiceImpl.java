package com.ruoyi.oversea.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.oversea.domain.OutboundApplication;
import com.ruoyi.oversea.mapper.OutboundApplicationMapper;
import com.ruoyi.oversea.service.OutboundApplicationService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/18 10:25
 */
@Service
@RequiredArgsConstructor
public class OutboundApplicationServiceImpl implements OutboundApplicationService {
    private final OutboundApplicationMapper outboundApplicationMapper;

    private final ISysUserService iSysUserService;

    private final UniversityServiceImpl universityService;

    private final AnnouncementServiceImpl announcementService;

    /**
     * 获取所有出境申请
     *
     * @return 所有出境申请列表
     */
    @Transactional
    @Override
    public List<OutboundApplication> getAllOutboundApplications() {
        List<OutboundApplication> allApplications = outboundApplicationMapper.getAllOutboundApplications();
        fillOutboundApplicationUserName(allApplications);
        fillAnnouncementTitle(allApplications);
        fillUniversityName(allApplications);
        return allApplications;
    }

    /**
     * 根据申请ID获取出境申请信息
     *
     * @param applicationId 申请ID
     * @return 出境申请信息
     */
    @Override
    public OutboundApplication getOutboundApplicationById(Long applicationId) {
        return outboundApplicationMapper.getOutboundApplicationById(applicationId);
    }

    /**
     * 添加出境申请
     *
     * @param outboundApplication 待添加的出境申请信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addOutboundApplication(OutboundApplication outboundApplication) {
        Long userId = SecurityUtils.getUserId();
        outboundApplication.setUserId(userId);
        int rows = outboundApplicationMapper.addOutboundApplication(outboundApplication);
        return rows > 0;
    }

    /**
     * 更新出境申请信息
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateOutboundApplication(OutboundApplication outboundApplication) {
        int rows = outboundApplicationMapper.updateOutboundApplication(outboundApplication);
        return rows > 0;
    }

    /**
     * 更新学院审核结果
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateCollegeReview(OutboundApplication outboundApplication) {
        Long userId = SecurityUtils.getUserId();
        outboundApplication.setCollegeReviewerId(userId);
        int rows = outboundApplicationMapper.updateCollegeReview(outboundApplication);
        return rows > 0;
    }

    /**
     * 更新学校审核结果
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateUniversityReview(OutboundApplication outboundApplication) {
        Long userId = SecurityUtils.getUserId();
        outboundApplication.setUniversityReviewerId(userId);
        int rows = outboundApplicationMapper.updateUniversityReview(outboundApplication);
        return rows > 0;
    }

    /**
     * 删除出境申请
     *
     * @param applicationId 待删除的出境申请ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteOutboundApplication(Long applicationId) {
        int rows = outboundApplicationMapper.deleteOutboundApplication(applicationId);
        return rows > 0;
    }

    /**
     * 根据学生ID获取出境申请
     *
     * @param userId 学生ID
     * @return 出境申请列表
     */
    @Override
    public List<OutboundApplication> getOutboundApplicationsByUserId(Long userId) {
        return outboundApplicationMapper.getOutboundApplicationsByUserId(userId);
    }

    /**
     * 获取特定审核状态的出境申请列表
     *
     * @param collegeReviewResult 学院审核结果
     * @param universityReviewResult 学校审核结果
     * @return 出境申请列表
     */
    @Override
    public List<OutboundApplication> getOutboundApplicationsByReviewStatus(Integer collegeReviewResult, Integer universityReviewResult) {
        return outboundApplicationMapper.getOutboundApplicationsByReviewStatus(collegeReviewResult, universityReviewResult);
    }

    /**
     * 填充相关用户姓名
     *
     * @param applications 出境申请列表
     */
    private void fillOutboundApplicationUserName(List<OutboundApplication> applications) {
        for (OutboundApplication application : applications) {
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

    /**
     * 填充出境申请的公告标题
     *
     * @param applications 出境申请列表
     */
    private void fillAnnouncementTitle(List<OutboundApplication> applications) {
        for (OutboundApplication application : applications) {
            Long announcementId = application.getAnnouncementId();
            String announcementTitle = announcementService.getAnnouncementById(announcementId).getTitle();
            application.setAnnouncementTitle(announcementTitle);
        }
    }

    private void fillUniversityName(List<OutboundApplication> applications) {
        for (OutboundApplication application : applications) {
            Long universityId = application.getUniversityId();
            String universityName = universityService.getUniversityById(universityId).getUniversityName();
            application.setUniversityName(universityName);
        }
    }
}
