package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.OeTrainProgram;
import com.ruoyi.oversea.mapper.OeTrainProgramMapper;
import com.ruoyi.oversea.service.OeTrainProgramService;
import com.ruoyi.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 9:43
 */
@Service
@RequiredArgsConstructor
public class OeTrainProgramServiceImpl implements OeTrainProgramService {
    private final OeTrainProgramMapper oeTrainProgramMapper;
    private final OeInMajorServiceImpl oeInMajorService;

    @Override
    public List<OeTrainProgram> getAllTrainPrograms() {
        List<OeTrainProgram> allTrainPrograms = oeTrainProgramMapper.getAllTrainPrograms();
        fillTrainProgram(allTrainPrograms);
        return allTrainPrograms;
    }

    @Override
    public OeTrainProgram getTrainProgramById(Long id) {
        return oeTrainProgramMapper.getTrainProgramById(id);
    }

    @Override
    public List<OeTrainProgram> getTrainProgramsByMajorId(Long majorId) {
        return oeTrainProgramMapper.getTrainProgramsByMajorId(majorId);
    }

    @Override
    public boolean addTrainProgram(OeTrainProgram oeTrainProgram) {
        return oeTrainProgramMapper.addTrainProgram(oeTrainProgram) > 0;
    }

    @Override
    public boolean updateTrainProgram(OeTrainProgram oeTrainProgram) {
        return oeTrainProgramMapper.updateTrainProgram(oeTrainProgram) > 0;
    }

    @Override
    public boolean deleteTrainProgram(Long id) {
        return oeTrainProgramMapper.deleteTrainProgram(id) > 0;
    }

    private void fillTrainProgram(List<OeTrainProgram> oeTrainPrograms) {
        for (OeTrainProgram oeTrainProgram : oeTrainPrograms) {
            Long majorId = oeTrainProgram.getMajorId();
            String majorName = oeInMajorService.getMajorById(majorId).getMajorName();
            oeTrainProgram.setMajorName(majorName);
        }
    }
}
