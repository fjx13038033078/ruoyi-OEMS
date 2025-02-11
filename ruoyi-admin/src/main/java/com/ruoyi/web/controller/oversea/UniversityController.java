package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.University;
import com.ruoyi.oversea.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:59
 */
@RestController
@RequestMapping("/oversea/university")
@RequiredArgsConstructor
public class UniversityController extends BaseController {
    private final UniversityService universityService;

    // 获取所有境外学校列表
    @GetMapping("/listAll")
    public TableDataInfo listAllUniversities() {
        startPage();
        List<University> allUniversities = universityService.getAllUniversities();
        return getDataTable(allUniversities);
    }

    // 根据境外学校ID获取境外学校详情
    @GetMapping("/detail")
    public AjaxResult getUniversityById(@RequestParam Long universityId) {
        return AjaxResult.success(universityService.getUniversityById(universityId));
    }

    // 添加境外学校
    @PostMapping("/add")
    public AjaxResult addUniversity(@RequestBody University university) {
        return toAjax(universityService.addUniversity(university));
    }

    // 更新境外学校信息
    @PostMapping("/update")
    public AjaxResult updateUniversity(@RequestBody University university) {
        return toAjax(universityService.updateUniversity(university));
    }

    // 删除境外学校
    @GetMapping("/delete")
    public AjaxResult deleteUniversity(@RequestParam Long universityId) {
        return toAjax(universityService.deleteUniversity(universityId));
    }
}
