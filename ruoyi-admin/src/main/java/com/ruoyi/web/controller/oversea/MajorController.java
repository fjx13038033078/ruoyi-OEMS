package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.Major;
import com.ruoyi.oversea.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author 范佳兴
 * @date 2025/3/14 14:35
 */
@RestController
@RequestMapping("/oversea/major")
@RequiredArgsConstructor
public class MajorController extends BaseController {
    private final MajorService majorService;

    @GetMapping("/listAll")
    public TableDataInfo listAllMajors(){
        startPage();
        List<Major> allMajors = majorService.getAllMajors();
        return getDataTable(allMajors);
    }

    @GetMapping("/listByUniversity")
    public AjaxResult listMajorsByUniversityId(@RequestParam Long universityId){
        List<Major> majorsByUniversityId = majorService.getMajorsByUniversityId(universityId);
        return success(majorsByUniversityId);
    }

    @GetMapping("/listByRApplicationId")
    public AjaxResult getMajorByRApplicationId(@RequestParam Long rApplicationId){
        List<Major> majorByRApplicationId = majorService.getMajorByRApplicationId(rApplicationId);
        return success(majorByRApplicationId);
    }

    @GetMapping("/detail")
    public AjaxResult getMajorById(@RequestParam Long majorId) {
        return AjaxResult.success(majorService.getMajorById(majorId));
    }

    @PostMapping("/add")
    public AjaxResult addMajor(@RequestBody Major major) {
        return toAjax(majorService.addMajor(major));
    }

    @PostMapping("/update")
    public AjaxResult updateMajor(@RequestBody Major major) {
        return toAjax(majorService.updateMajor(major));
    }

    @GetMapping("/delete")
    public AjaxResult deleteMajor(@RequestParam Long majorId) {
        return toAjax(majorService.deleteMajor(majorId));
    }
}
