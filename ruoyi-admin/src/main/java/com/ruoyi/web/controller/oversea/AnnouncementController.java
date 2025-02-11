package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.Announcement;
import com.ruoyi.oversea.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:56
 */
@RestController
@RequestMapping("/oversea/announcement")
@RequiredArgsConstructor
public class AnnouncementController extends BaseController {
    private final AnnouncementService announcementService;

    // 获取所有公告列表
    @GetMapping("/listAll")
    public TableDataInfo listAllAnnouncements() {
        startPage();
        List<Announcement> allAnnouncements = announcementService.getAllAnnouncements();
        return getDataTable(allAnnouncements);
    }

    // 根据公告ID获取公告详情
    @GetMapping("/detail")
    public AjaxResult getAnnouncementById(@RequestParam Long announcementId) {
        return AjaxResult.success(announcementService.getAnnouncementById(announcementId));
    }

    // 添加公告
    @PostMapping("/add")
    public AjaxResult addAnnouncement(@RequestBody Announcement announcement) {
        return toAjax(announcementService.addAnnouncement(announcement));
    }

    // 更新公告信息
    @PostMapping("/update")
    public AjaxResult updateAnnouncement(@RequestBody Announcement announcement) {
        return toAjax(announcementService.updateAnnouncement(announcement));
    }

    // 删除公告
    @GetMapping("/delete")
    public AjaxResult deleteAnnouncement(@RequestParam Long announcementId) {
        return toAjax(announcementService.deleteAnnouncement(announcementId));
    }
}
