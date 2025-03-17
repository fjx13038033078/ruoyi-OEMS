package com.ruoyi.oversea.service.impl;

import com.ruoyi.oversea.domain.OeInMajor;
import com.ruoyi.oversea.mapper.OeInMajorMapper;
import com.ruoyi.oversea.service.OeInMajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/3/17 8:58
 */
@Service
@RequiredArgsConstructor
public class OeInMajorServiceImpl implements OeInMajorService {
    private final OeInMajorMapper oeInMajorMapper;

    /**
     * 获取所有专业信息
     */
    @Transactional
    @Override
    public List<OeInMajor> getAllMajors() {
        return oeInMajorMapper.getAllMajors();
    }

    /**
     * 根据专业ID获取专业详情
     */
    @Override
    public OeInMajor getMajorById(Long majorId) {
        return oeInMajorMapper.getMajorById(majorId);
    }

    /**
     * 添加专业
     */
    @Override
    public boolean addMajor(OeInMajor major) {
        int rows = oeInMajorMapper.addMajor(major);
        return rows > 0;
    }

    /**
     * 更新专业信息
     */
    @Override
    public boolean updateMajor(OeInMajor major) {
        int rows = oeInMajorMapper.updateMajor(major);
        return rows > 0;
    }

    /**
     * 删除专业
     */
    @Override
    public boolean deleteMajor(Long majorId) {
        int rows = oeInMajorMapper.deleteMajor(majorId);
        return rows > 0;
    }
}
