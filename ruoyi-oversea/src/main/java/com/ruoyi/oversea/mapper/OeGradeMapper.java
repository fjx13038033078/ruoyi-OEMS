package com.ruoyi.oversea.mapper;

import com.ruoyi.oversea.domain.OeGrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 10:19
 */
@Mapper
public interface OeGradeMapper {
    /**
     * 获取所有成绩
     */
    List<OeGrade> getAllGrades();

    /**
     * 根据成绩ID获取成绩
     */
    OeGrade getGradeById(Long gradeId);

    /**
     * 根据学生ID获取该学生的所有成绩
     */
    List<OeGrade> getGradesByStudentId(Long studentId);

    /**
     * 根据课程ID获取该课程的所有成绩
     */
    List<OeGrade> getGradesByCourseId(Long courseId);

    /**
     * 添加成绩
     */
    int addGrade(OeGrade oeGrade);

    /**
     * 更新成绩
     */
    int updateGrade(OeGrade oeGrade);

    /**
     * 删除成绩
     */
    int deleteGrade(Long gradeId);
}
