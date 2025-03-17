package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.OeTrainProgram;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 9:43
 */
public interface OeTrainProgramService {
    /**
     * 获取所有培养方案
     */
    List<OeTrainProgram> getAllTrainPrograms();

    /**
     * 根据ID获取培养方案
     */
    OeTrainProgram getTrainProgramById(Long id);

    /**
     * 根据专业ID获取培养方案
     */
    List<OeTrainProgram> getTrainProgramsByMajorId(Long majorId);

    /**
     * 添加培养方案
     */
    boolean addTrainProgram(OeTrainProgram oeTrainProgram);

    /**
     * 更新培养方案
     */
    boolean updateTrainProgram(OeTrainProgram oeTrainProgram);

    /**
     * 删除培养方案
     */
    boolean deleteTrainProgram(Long id);
}
