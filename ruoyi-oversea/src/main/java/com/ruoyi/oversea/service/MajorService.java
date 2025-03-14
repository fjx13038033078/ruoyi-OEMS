package com.ruoyi.oversea.service;

import com.ruoyi.oversea.domain.Major;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/14 14:34
 */
public interface MajorService {

    List<Major> getAllMajors();

    Major getMajorById(Long majorId);

    List<Major> getMajorsByUniversityId(Long universityId);

    boolean addMajor(Major major);

    boolean updateMajor(Major major);

    boolean deleteMajor(Long majorId);
}
