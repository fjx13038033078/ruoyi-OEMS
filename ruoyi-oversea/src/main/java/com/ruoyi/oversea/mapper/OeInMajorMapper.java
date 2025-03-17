package com.ruoyi.oversea.mapper;

import com.ruoyi.oversea.domain.OeInMajor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 8:52
 */
@Mapper
public interface OeInMajorMapper {
    /**
     * 查询所有专业
     * @return 专业列表
     */
    List<OeInMajor> getAllMajors();

    /**
     * 根据ID查询专业
     * @param id 专业ID
     * @return 专业信息
     */
    OeInMajor getMajorById(Long id);

    /**
     * 添加专业
     * @param major 专业对象
     * @return 影响的行数
     */
    int addMajor(OeInMajor major);

    /**
     * 更新专业信息
     * @param major 专业对象
     * @return 影响的行数
     */
    int updateMajor(OeInMajor major);

    /**
     * 删除专业
     * @param id 专业ID
     * @return 影响的行数
     */
    int deleteMajor(Long id);
}
