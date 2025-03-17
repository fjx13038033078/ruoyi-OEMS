package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.OeTrainProgram;
import com.ruoyi.oversea.service.OeTrainProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 9:45
 */
@RestController
@RequestMapping("/oversea/trainProgram")
@RequiredArgsConstructor
public class OeTrainProgramController extends BaseController {
    private final OeTrainProgramService oeTrainProgramService;

    // 查询所有培养方案
    @GetMapping("/listAll")
    public TableDataInfo listAllTrainPrograms() {
        startPage();
        List<OeTrainProgram> list = oeTrainProgramService.getAllTrainPrograms();
        return getDataTable(list);
    }

    // 根据ID获取培养方案
    @GetMapping("/detail")
    public AjaxResult getTrainProgramById(@RequestParam Long id) {
        return AjaxResult.success(oeTrainProgramService.getTrainProgramById(id));
    }

    // 根据专业ID获取培养方案
    @GetMapping("/listByMajor")
    public AjaxResult getTrainProgramsByMajorId(@RequestParam Long majorId) {
        return AjaxResult.success(oeTrainProgramService.getTrainProgramsByMajorId(majorId));
    }

    // 添加培养方案
    @PostMapping("/add")
    public AjaxResult addTrainProgram(@RequestBody OeTrainProgram oeTrainProgram) {
        return toAjax(oeTrainProgramService.addTrainProgram(oeTrainProgram));
    }

    // 更新培养方案
    @PostMapping("/update")
    public AjaxResult updateTrainProgram(@RequestBody OeTrainProgram oeTrainProgram) {
        return toAjax(oeTrainProgramService.updateTrainProgram(oeTrainProgram));
    }

    // 删除培养方案
    @GetMapping("/delete")
    public AjaxResult deleteTrainProgram(@RequestParam Long id) {
        return toAjax(oeTrainProgramService.deleteTrainProgram(id));
    }
}
