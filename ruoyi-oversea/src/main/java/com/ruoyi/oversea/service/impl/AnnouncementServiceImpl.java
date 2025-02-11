package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.Announcement;
import com.ruoyi.oversea.mapper.AnnouncementMapper;
import com.ruoyi.oversea.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:47
 */
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementMapper announcementMapper;


    /**
     * 获取所有公告
     *
     * @return 所有公告列表
     */
    @Transactional
    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.getAllAnnouncements();
    }

    /**
     * 根据公告ID获取公告信息
     *
     * @param announcementId 公告ID
     * @return 公告信息
     */
    @Override
    public Announcement getAnnouncementById(Long announcementId) {
        return announcementMapper.getAnnouncementById(announcementId);
    }

    /**
     * 添加公告
     *
     * @param announcement 待添加的公告信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addAnnouncement(Announcement announcement) {
        int rows = announcementMapper.addAnnouncement(announcement);
        return rows > 0;
    }

    /**
     * 更新公告信息
     *
     * @param announcement 待更新的公告信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateAnnouncement(Announcement announcement) {
        int rows = announcementMapper.updateAnnouncement(announcement);
        return rows > 0;
    }

    /**
     * 删除公告
     *
     * @param announcementId 待删除的公告ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteAnnouncement(Long announcementId) {
        int rows = announcementMapper.deleteAnnouncement(announcementId);
        return rows > 0;
    }
}
