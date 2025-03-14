package com.ruoyi.oversea.mapper;

import com.ruoyi.oversea.domain.OeCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/14 15:24
 */
@Mapper
public interface OeCourseMapper {
    /**
     * 获取所有课程
     * @return
     */
    List<OeCourse> selectAllCourses();

    /**
     * 根据课程id获取课程
     * @param courseId
     * @return
     */
    OeCourse selectCourseById(Long courseId);

    /**
     * 添加课程
     * @param course
     * @return
     */
    int insertCourse(OeCourse course);

    /**
     * 更新课程
     * @param course
     * @return
     */
    int updateCourse(OeCourse course);


    int deleteCourse(Long courseId);
}
