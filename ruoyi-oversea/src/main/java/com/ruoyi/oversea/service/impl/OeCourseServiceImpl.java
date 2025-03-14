package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.OeCourse;
import com.ruoyi.oversea.mapper.OeCourseMapper;
import com.ruoyi.oversea.service.OeCourseService;
import com.ruoyi.oversea.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/14 15:39
 */
@Service
@RequiredArgsConstructor
public class OeCourseServiceImpl implements OeCourseService {
    private final OeCourseMapper oeCourseMapper;

    private final UniversityService universityService;
    /**
     * 获取所有课程
     */
    @Transactional
    @Override
    public List<OeCourse> getAllCourses() {
        List<OeCourse> oeCourses = oeCourseMapper.selectAllCourses();
        fillUniversityName(oeCourses);
        return oeCourses;
    }

    /**
     * 根据ID获取课程信息
     */
    @Override
    public OeCourse getCourseById(Long courseId) {
        return oeCourseMapper.selectCourseById(courseId);
    }

    /**
     * 添加课程
     */
    @Override
    public boolean addCourse(OeCourse oeCourse) {
        return oeCourseMapper.insertCourse(oeCourse) > 0;
    }

    /**
     * 更新课程信息
     */
    @Override
    public boolean updateCourse(OeCourse oeCourse) {
        return oeCourseMapper.updateCourse(oeCourse) > 0;
    }

    /**
     * 删除课程
     */
    @Override
    public boolean deleteCourse(Long courseId) {
        return oeCourseMapper.deleteCourse(courseId) > 0;
    }

    private void fillUniversityName(List<OeCourse> courses) {
        if (courses != null && courses.size() > 0) {
            for (OeCourse course : courses) {
                Long universityId = course.getUniversityId();
                String universityName = universityService.getUniversityById(universityId).getUniversityName();
                course.setUniversityName(universityName);
            }
        }
    }
}
