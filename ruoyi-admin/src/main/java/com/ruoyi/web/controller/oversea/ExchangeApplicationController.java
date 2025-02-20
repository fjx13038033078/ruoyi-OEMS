package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.ExchangeApplication;
import com.ruoyi.oversea.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/20 9:12
 */
@RestController
@RequestMapping("/oversea/exchangeApplication")
@RequiredArgsConstructor
public class ExchangeApplicationController extends BaseController {
    private final ExchangeApplicationService exchangeApplicationService;

    // 获取所有学分置换申请列表
    @GetMapping("/listAll")
    public TableDataInfo listAllExchangeApplications() {
        startPage();
        List<ExchangeApplication> allApplications = exchangeApplicationService.getAllExchangeApplications();
        return getDataTable(allApplications);
    }

    // 根据申请ID获取学分置换申请详情
    @GetMapping("/detail")
    public AjaxResult getExchangeApplicationById(@RequestParam Long applicationId) {
        return AjaxResult.success(exchangeApplicationService.getExchangeApplicationById(applicationId));
    }

    // 添加学分置换申请
    @PostMapping("/add")
    public AjaxResult addExchangeApplication(@RequestBody ExchangeApplication exchangeApplication) {
        return toAjax(exchangeApplicationService.addExchangeApplication(exchangeApplication));
    }

    // 更新学分置换申请信息
    @PostMapping("/update")
    public AjaxResult updateExchangeApplication(@RequestBody ExchangeApplication exchangeApplication) {
        return toAjax(exchangeApplicationService.updateExchangeApplication(exchangeApplication));
    }

    // 更新学院审核结果
    @PostMapping("/update/college")
    public AjaxResult updateCollegeReview(@RequestBody ExchangeApplication exchangeApplication) {
        boolean result = exchangeApplicationService.updateCollegeReview(exchangeApplication);
        return toAjax(result);
    }

    // 更新学校审核结果
    @PostMapping("/update/university")
    public AjaxResult updateUniversityReview(@RequestBody ExchangeApplication exchangeApplication) {
        boolean result = exchangeApplicationService.updateUniversityReview(exchangeApplication);
        return toAjax(result);
    }

    // 删除学分置换申请
    @GetMapping("/delete")
    public AjaxResult deleteExchangeApplication(@RequestParam Long applicationId) {
        return toAjax(exchangeApplicationService.deleteExchangeApplication(applicationId));
    }

    // 根据学生ID获取学分置换申请列表
    @GetMapping("/listByUserId")
    public TableDataInfo listExchangeApplicationsByUserId(@RequestParam Long userId) {
        startPage();
        List<ExchangeApplication> applications = exchangeApplicationService.getExchangeApplicationsByUserId(userId);
        return getDataTable(applications);
    }
}
