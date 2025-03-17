package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.OeInMajor;
import com.ruoyi.oversea.service.OeInMajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 8:59
 */
@RestController
@RequestMapping("/oversea/inMajor")
@RequiredArgsConstructor
public class OeInMajorController extends BaseController {

    private final OeInMajorService oeInMajorService;

    // 获取所有专业列表
    @GetMapping("/listAll")
    public TableDataInfo listAllInMajors() {
        startPage();
        List<OeInMajor> allMajors = oeInMajorService.getAllMajors();
        return getDataTable(allMajors);
    }

    // 根据专业ID获取专业详情
    @GetMapping("/detail")
    public AjaxResult getInMajorById(@RequestParam Long majorId) {
        return AjaxResult.success(oeInMajorService.getMajorById(majorId));
    }

    // 添加专业
    @PostMapping("/add")
    public AjaxResult addInMajor(@RequestBody OeInMajor major) {
        return toAjax(oeInMajorService.addMajor(major));
    }

    // 更新专业信息
    @PostMapping("/update")
    public AjaxResult updateInMajor(@RequestBody OeInMajor major) {
        return toAjax(oeInMajorService.updateMajor(major));
    }

    // 删除专业
    @GetMapping("/delete")
    public AjaxResult deleteInMajor(@RequestParam Long majorId) {
        return toAjax(oeInMajorService.deleteMajor(majorId));
    }
}
