package com.ruoyi.oversea.mapper;

import com.ruoyi.oversea.domain.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:44
 */
@Mapper
public interface AnnouncementMapper {
    /**
     * 获取所有公告
     *
     * @return 所有公告列表
     */
    List<Announcement> getAllAnnouncements();

    /**
     * 根据公告ID获取公告信息
     *
     * @param announcementId 公告ID
     * @return 公告信息
     */
    Announcement getAnnouncementById(Long announcementId);

    /**
     * 添加公告
     *
     * @param announcement 待添加的公告信息
     * @return 添加成功返回影响的行数，否则返回0
     */
    int addAnnouncement(Announcement announcement);

    /**
     * 更新公告信息
     *
     * @param announcement 待更新的公告信息
     * @return 更新成功返回影响的行数，否则返回0
     */
    int updateAnnouncement(Announcement announcement);

    /**
     * 删除公告
     *
     * @param announcementId 待删除的公告ID
     * @return 删除成功返回影响的行数，否则返回0
     */
    int deleteAnnouncement(Long announcementId);
}
