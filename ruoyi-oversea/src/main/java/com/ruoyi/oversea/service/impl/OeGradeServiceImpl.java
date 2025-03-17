package com.ruoyi.oversea.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.oversea.domain.OeGrade;
import com.ruoyi.oversea.mapper.OeGradeMapper;
import com.ruoyi.oversea.service.OeCourseService;
import com.ruoyi.oversea.service.OeGradeService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 14:15
 */
@Service
@RequiredArgsConstructor
public class OeGradeServiceImpl implements OeGradeService {

    private final OeGradeMapper gradeMapper;

    private final ISysUserService iSysUserService;

    private final OeCourseService oeCourseService;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有成绩信息
     */
    @Override
    public List<OeGrade> getAllGrades() {
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equals("student")){
            List<OeGrade> gradesByStudentId = gradeMapper.getGradesByStudentId(userId);
            fillGradeInfo(gradesByStudentId);
            return gradesByStudentId;
        } else {
            List<OeGrade> allGrades = gradeMapper.getAllGrades();
            fillGradeInfo(allGrades);
            return allGrades;
        }
    }

    /**
     * 根据成绩ID获取成绩信息
     */
    @Override
    public OeGrade getGradeById(Long gradeId) {
        return gradeMapper.getGradeById(gradeId);
    }

    /**
     * 根据学生ID获取成绩信息
     */
    @Override
    public List<OeGrade> getGradesByStudentId(Long studentId) {
        return gradeMapper.getGradesByStudentId(studentId);
    }

    /**
     * 添加成绩
     */
    @Override
    public boolean addGrade(OeGrade grade) {
        return gradeMapper.addGrade(grade) > 0;
    }

    /**
     * 更新成绩信息
     */
    @Override
    public boolean updateGrade(OeGrade grade) {
        return gradeMapper.updateGrade(grade) > 0;
    }

    /**
     * 删除成绩
     */
    @Override
    public boolean deleteGrade(Long gradeId) {
        return gradeMapper.deleteGrade(gradeId) > 0;
    }

    private void fillGradeInfo(List<OeGrade> grades) {
        for (OeGrade oeGrade : grades) {
            // 填充学生信息
            if (oeGrade.getStudentId() != null)
                oeGrade.setStudentName(iSysUserService.selectUserById(oeGrade.getStudentId()).getNickName());
            // 填充课程信息
            if (oeGrade.getCourseId() != null)
                oeGrade.setCourseName(oeCourseService.getCourseById(oeGrade.getCourseId()).getCourseName());
        }
    }
}
