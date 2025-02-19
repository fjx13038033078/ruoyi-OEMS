package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.OutboundApplication;
import com.ruoyi.oversea.service.OutboundApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/18 10:28
 */
@RestController
@RequestMapping("/oversea/outboundApplication")
@RequiredArgsConstructor
public class OutboundApplicationController extends BaseController {
    private final OutboundApplicationService outboundApplicationService;

    // 获取所有出境申请列表
    @GetMapping("/listAll")
    public TableDataInfo listAllOutboundApplications() {
        startPage();
        List<OutboundApplication> allApplications = outboundApplicationService.getAllOutboundApplications();
        return getDataTable(allApplications);
    }

    // 根据申请ID获取出境申请详情
    @GetMapping("/detail")
    public AjaxResult getOutboundApplicationById(@RequestParam Long applicationId) {
        return AjaxResult.success(outboundApplicationService.getOutboundApplicationById(applicationId));
    }

    // 添加出境申请
    @PostMapping("/add")
    public AjaxResult addOutboundApplication(@RequestBody OutboundApplication outboundApplication) {
        return toAjax(outboundApplicationService.addOutboundApplication(outboundApplication));
    }

    // 更新出境申请信息
    @PostMapping("/update")
    public AjaxResult updateOutboundApplication(@RequestBody OutboundApplication outboundApplication) {
        return toAjax(outboundApplicationService.updateOutboundApplication(outboundApplication));
    }

    /**
     * 更新学院审核结果
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 操作结果
     */
    @PostMapping("/update/college")
    public AjaxResult updateCollegeReview(@RequestBody OutboundApplication outboundApplication) {
        boolean result = outboundApplicationService.updateCollegeReview(outboundApplication);
        return toAjax(result);
    }

    /**
     * 更新学校审核结果
     *
     * @param outboundApplication 待更新的出境申请信息
     * @return 操作结果
     */
    @PostMapping("/update/university")
    public AjaxResult updateUniversityReview(@RequestBody OutboundApplication outboundApplication) {
        boolean result = outboundApplicationService.updateUniversityReview(outboundApplication);
        return toAjax(result);
    }

    // 删除出境申请
    @GetMapping("/delete")
    public AjaxResult deleteOutboundApplication(@RequestParam Long applicationId) {
        return toAjax(outboundApplicationService.deleteOutboundApplication(applicationId));
    }

    // 根据学生ID获取出境申请列表
    @GetMapping("/listByUserId")
    public TableDataInfo listOutboundApplicationsByUserId(@RequestParam Long userId) {
        startPage();
        List<OutboundApplication> applications = outboundApplicationService.getOutboundApplicationsByUserId(userId);
        return getDataTable(applications);
    }

    // 获取特定审核状态的出境申请列表
    @GetMapping("/listByReviewStatus")
    public TableDataInfo listOutboundApplicationsByReviewStatus(@RequestParam Integer collegeReviewResult,
                                                                @RequestParam Integer universityReviewResult) {
        startPage();
        List<OutboundApplication> applications = outboundApplicationService.getOutboundApplicationsByReviewStatus(collegeReviewResult, universityReviewResult);
        return getDataTable(applications);
    }
}
