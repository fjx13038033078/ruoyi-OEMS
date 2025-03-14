package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.ExchangeApplication;
import com.ruoyi.oversea.domain.Major;
import com.ruoyi.oversea.domain.OutboundApplication;
import com.ruoyi.oversea.domain.ReinstatementApplication;
import com.ruoyi.oversea.mapper.MajorMapper;
import com.ruoyi.oversea.service.ExchangeApplicationService;
import com.ruoyi.oversea.service.MajorService;
import com.ruoyi.oversea.service.OutboundApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/14 14:35
 */
@Service
@RequiredArgsConstructor
public class MajorServiceImpl implements MajorService {
    private final MajorMapper majorMapper;
    private final ReinstatementApplicationServiceImpl reinstatementApplicationService;
    private final OutboundApplicationService outboundApplicationService;

    @Transactional
    @Override
    public List<Major> getAllMajors() {
        return majorMapper.getAllMajors();
    }

    @Override
    public Major getMajorById(Long majorId) {
        return majorMapper.getMajorById(majorId);
    }

    @Override
    public List<Major> getMajorsByUniversityId(Long universityId) {
        return majorMapper.getMajorsByUniversityId(universityId);
    }

    @Override
    public List<Major> getMajorByRApplicationId(Long rApplicationId) {
        ReinstatementApplication reinstatementApplicationById = reinstatementApplicationService.getReinstatementApplicationById(rApplicationId);
        Long outboundApplicationId = reinstatementApplicationById.getOutboundApplicationId();
        OutboundApplication outboundApplicationById = outboundApplicationService.getOutboundApplicationById(outboundApplicationId);
        Long universityId = outboundApplicationById.getUniversityId();
        List<Major> majorsByUniversityId = majorMapper.getMajorsByUniversityId(universityId);
        return majorsByUniversityId;
    }

    @Override
    public boolean addMajor(Major major) {
        return majorMapper.addMajor(major) > 0;
    }

    @Override
    public boolean updateMajor(Major major) {
        return majorMapper.updateMajor(major) > 0;
    }

    @Override
    public boolean deleteMajor(Long majorId) {
        return majorMapper.deleteMajor(majorId) > 0;
    }
}
