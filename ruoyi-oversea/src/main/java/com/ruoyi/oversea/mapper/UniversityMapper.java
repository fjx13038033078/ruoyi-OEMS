package com.ruoyi.oversea.mapper;

import com.ruoyi.oversea.domain.University;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:36
 */
@Mapper
public interface UniversityMapper {
    /**
     * 获取所有境外学校
     *
     * @return 所有境外学校列表
     */
    List<University> getAllUniversities();

    /**
     * 根据境外学校ID获取境外学校信息
     *
     * @param universityId 境外学校ID
     * @return 境外学校信息
     */
    University getUniversityById(Long universityId);

    /**
     * 添加境外学校
     *
     * @param university 待添加的境外学校信息
     * @return 添加成功返回影响的行数，否则返回0
     */
    int addUniversity(University university);

    /**
     * 更新境外学校信息
     *
     * @param university 待更新的境外学校信息
     * @return 更新成功返回影响的行数，否则返回0
     */
    int updateUniversity(University university);

    /**
     * 删除境外学校
     *
     * @param universityId 待删除的境外学校ID
     * @return 删除成功返回影响的行数，否则返回0
     */
    int deleteUniversity(Long universityId);
}
