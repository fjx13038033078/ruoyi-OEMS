package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.University;
import com.ruoyi.oversea.mapper.UniversityMapper;
import com.ruoyi.oversea.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/11 17:42
 */
@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityMapper universityMapper;


    @Transactional
    @Override
    public List<University> getAllUniversities() {
        return universityMapper.getAllUniversities();
    }

    @Override
    public University getUniversityById(Long universityId) {
        return universityMapper.getUniversityById(universityId);
    }

    @Override
    public boolean addUniversity(University university) {
        int rows = universityMapper.addUniversity(university);
        return rows > 0;
    }

    @Override
    public boolean updateUniversity(University university) {
        int rows = universityMapper.updateUniversity(university);
        return rows > 0;
    }

    @Override
    public boolean deleteUniversity(Long universityId) {
        int rows = universityMapper.deleteUniversity(universityId);
        return rows > 0;
    }
}
