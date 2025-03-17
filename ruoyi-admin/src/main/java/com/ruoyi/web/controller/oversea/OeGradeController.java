package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.OeGrade;
import com.ruoyi.oversea.service.OeGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author 范佳兴
 * @date 2025/3/17 14:15
 */
@RestController
@RequestMapping("/oversea/grade")
@RequiredArgsConstructor
public class OeGradeController  extends BaseController {
    private final OeGradeService gradeService;

    @GetMapping("/listAll")
    public TableDataInfo listAllGrades() {
        startPage();
        List<OeGrade> allGrades = gradeService.getAllGrades();
        return getDataTable(allGrades);
    }

    @GetMapping("/detail")
    public AjaxResult getGradeById(@RequestParam Long gradeId) {
        return AjaxResult.success(gradeService.getGradeById(gradeId));
    }

    @PostMapping("/add")
    public AjaxResult addGrade(@RequestBody OeGrade grade) {
        return toAjax(gradeService.addGrade(grade));
    }

    @PostMapping("/update")
    public AjaxResult updateGrade(@RequestBody OeGrade grade) {
        return toAjax(gradeService.updateGrade(grade));
    }

    @GetMapping("/delete")
    public AjaxResult deleteGrade(@RequestParam Long gradeId) {
        return toAjax(gradeService.deleteGrade(gradeId));
    }
}
