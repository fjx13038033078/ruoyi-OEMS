package com.ruoyi.oversea.mapper;

import com.ruoyi.oversea.domain.Major;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * @Author 范佳兴
 * @date 2025/3/14 14:31
 */
@Mapper
public interface MajorMapper {
    /**
     * 获取所有专业信息
     */
    List<Major> getAllMajors();

    /**
     * 根据专业ID获取专业信息
     */
    Major getMajorById(Long majorId);

    /**
     * 根据高校ID获取专业信息
     */
    List<Major> getMajorsByUniversityId(Long universityId);

    /**
     * 添加专业
     */
    int addMajor(Major major);

    /**
     * 更新专业信息
     */
    int updateMajor(Major major);

    /**
     * 删除专业（逻辑删除）
     */
    int deleteMajor(Long majorId);
}
