package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.OeGrade;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 14:14
 */
public interface OeGradeService {
    /**
     * 获取所有成绩信息
     */
    List<OeGrade> getAllGrades();

    /**
     * 根据成绩ID获取成绩信息
     */
    OeGrade getGradeById(Long gradeId);

    /**
     * 根据学生ID获取成绩信息
     */
    List<OeGrade> getGradesByStudentId(Long studentId);

    /**
     * 添加成绩
     */
    boolean addGrade(OeGrade grade);

    /**
     * 更新成绩信息
     */
    boolean updateGrade(OeGrade grade);

    /**
     * 删除成绩
     */
    boolean deleteGrade(Long gradeId);
}
