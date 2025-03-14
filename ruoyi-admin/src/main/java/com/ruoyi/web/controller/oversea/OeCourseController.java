package com.ruoyi.web.controller.oversea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.oversea.domain.OeCourse;
import com.ruoyi.oversea.service.OeCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/14 15:41
 */
@RestController
@RequestMapping("/oversea/course")
@RequiredArgsConstructor
public class OeCourseController extends BaseController {
    private final OeCourseService oeCourseService;

    /**
     * 获取所有课程列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllCourses() {
        startPage();
        List<OeCourse> allCourses = oeCourseService.getAllCourses();
        return getDataTable(allCourses);
    }

    /**
     * 根据课程ID获取课程信息
     */
    @GetMapping("/detail")
    public AjaxResult getCourseById(@RequestParam Long courseId) {
        return AjaxResult.success(oeCourseService.getCourseById(courseId));
    }

    /**
     * 添加课程
     */
    @PostMapping("/add")
    public AjaxResult addCourse(@RequestBody OeCourse oeCourse) {
        return toAjax(oeCourseService.addCourse(oeCourse));
    }

    /**
     * 更新课程信息
     */
    @PostMapping("/update")
    public AjaxResult updateCourse(@RequestBody OeCourse oeCourse) {
        return toAjax(oeCourseService.updateCourse(oeCourse));
    }

    /**
     * 删除课程
     */
    @GetMapping("/delete")
    public AjaxResult deleteCourse(@RequestParam Long courseId) {
        return toAjax(oeCourseService.deleteCourse(courseId));
    }
}
