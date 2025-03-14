package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.Major;
import com.ruoyi.oversea.mapper.MajorMapper;
import com.ruoyi.oversea.service.MajorService;
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
