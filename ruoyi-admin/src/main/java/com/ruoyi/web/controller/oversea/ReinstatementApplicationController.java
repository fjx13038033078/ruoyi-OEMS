package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.ReinstatementApplication;
import com.ruoyi.oversea.service.ReinstatementApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/19 16:29
 */
@RestController
@RequestMapping("/oversea/reinstatementApplication")
@RequiredArgsConstructor
public class ReinstatementApplicationController extends BaseController {

    private final ReinstatementApplicationService reinstatementApplicationService;

    // 获取所有复学申请列表
    @GetMapping("/listAll")
    public TableDataInfo listAllReinstatementApplications() {
        startPage();
        List<ReinstatementApplication> allApplications = reinstatementApplicationService.getAllReinstatementApplications();
        return getDataTable(allApplications);
    }

    // 根据申请ID获取复学申请详情
    @GetMapping("/detail")
    public AjaxResult getReinstatementApplicationById(@RequestParam Long applicationId) {
        return AjaxResult.success(reinstatementApplicationService.getReinstatementApplicationById(applicationId));
    }

    // 添加复学申请
    @PostMapping("/add")
    public AjaxResult addReinstatementApplication(@RequestBody ReinstatementApplication reinstatementApplication) {
        return toAjax(reinstatementApplicationService.addReinstatementApplication(reinstatementApplication));
    }

    // 更新复学申请信息
    @PostMapping("/update")
    public AjaxResult updateReinstatementApplication(@RequestBody ReinstatementApplication reinstatementApplication) {
        return toAjax(reinstatementApplicationService.updateReinstatementApplication(reinstatementApplication));
    }

    /**
     * 更新学院审核结果
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 操作结果
     */
    @PostMapping("/update/college")
    public AjaxResult updateCollegeReview(@RequestBody ReinstatementApplication reinstatementApplication) {
        boolean result = reinstatementApplicationService.updateCollegeReview(reinstatementApplication);
        return toAjax(result);
    }

    /**
     * 更新学校审核结果
     *
     * @param reinstatementApplication 待更新的复学申请信息
     * @return 操作结果
     */
    @PostMapping("/update/university")
    public AjaxResult updateUniversityReview(@RequestBody ReinstatementApplication reinstatementApplication) {
        boolean result = reinstatementApplicationService.updateUniversityReview(reinstatementApplication);
        return toAjax(result);
    }

    // 删除复学申请
    @GetMapping("/delete")
    public AjaxResult deleteReinstatementApplication(@RequestParam Long applicationId) {
        return toAjax(reinstatementApplicationService.deleteReinstatementApplication(applicationId));
    }

    // 根据学生ID获取复学申请列表
    @GetMapping("/listByUserId")
    public TableDataInfo listReinstatementApplicationsByUserId(@RequestParam Long userId) {
        startPage();
        List<ReinstatementApplication> applications = reinstatementApplicationService.getReinstatementApplicationsByUserId(userId);
        return getDataTable(applications);
    }

    // 获取特定审核状态的复学申请列表
    @GetMapping("/listByReviewStatus")
    public TableDataInfo listReinstatementApplicationsByReviewStatus(@RequestParam Integer collegeReviewResult,
                                                                     @RequestParam Integer universityReviewResult) {
        startPage();
        List<ReinstatementApplication> applications = reinstatementApplicationService.getReinstatementApplicationsByReviewStatus(collegeReviewResult, universityReviewResult);
        return getDataTable(applications);
    }
}
