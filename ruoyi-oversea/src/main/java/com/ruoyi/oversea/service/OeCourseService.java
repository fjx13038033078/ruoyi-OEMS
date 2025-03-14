package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.Major;
import com.ruoyi.oversea.domain.OeCourse;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/14 15:39
 */
public interface OeCourseService {
    /**
     * 获取所有课程
     *
     * @return 课程列表
     */
    List<OeCourse> getAllCourses();

    /**
     * 根据ID获取课程信息
     *
     * @param courseId 课程ID
     * @return 课程信息
     */
    OeCourse getCourseById(Long courseId);

    List<OeCourse> getCourseByRApplicationId(Long rApplicationId);

    /**
     * 添加课程
     *
     * @param oeCourse 课程信息
     * @return 是否添加成功
     */
    boolean addCourse(OeCourse oeCourse);

    /**
     * 更新课程信息
     *
     * @param oeCourse 课程信息
     * @return 是否更新成功
     */
    boolean updateCourse(OeCourse oeCourse);

    /**
     * 删除课程
     *
     * @param courseId 课程ID
     * @return 是否删除成功
     */
    boolean deleteCourse(Long courseId);
}
