package com.ruoyi.web.controller.culture;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.culture.domain.ExhibitionRegistration;
import com.ruoyi.culture.service.ExhibitionRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 非遗展览报名管理 Controller
 *
 * @Author fanjaixing
 * @Date 2024/3/18 21:31
 */
@RestController
@RequestMapping("/culture/exhibition/registration")
@RequiredArgsConstructor
public class ExhibitionRegistrationController extends BaseController {

    private final ExhibitionRegistrationService exhibitionRegistrationService;

    // 获取所有非遗展览报名列表
    @GetMapping("/listAll")
    public TableDataInfo listAllExhibitionRegistrations() {
        startPage();
        List<ExhibitionRegistration> allRegistrations = exhibitionRegistrationService.getAllExhibitionRegistrations();
        return getDataTable(allRegistrations);
    }

    // 根据报名ID获取展览报名信息
    @GetMapping("/detail/{registrationId}")
    public AjaxResult getExhibitionRegistrationById(@PathVariable Long registrationId) {
        return AjaxResult.success(exhibitionRegistrationService.getExhibitionRegistrationById(registrationId));
    }

    // 添加非遗展览报名
    @PostMapping("/add")
    public AjaxResult addExhibitionRegistration(@RequestBody ExhibitionRegistration registration) {
        return toAjax(exhibitionRegistrationService.addExhibitionRegistration(registration));
    }

    // 删除非遗展览报名
    @DeleteMapping("/delete/{registrationId}")
    public AjaxResult deleteExhibitionRegistration(@PathVariable Long registrationId) {
        return toAjax(exhibitionRegistrationService.deleteExhibitionRegistration(registrationId));
    }
}
