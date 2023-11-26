package com.ricardo.web.dao;

import com.ricardo.web.dto.WorkExperienceDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface WorkExperienceDAO {
    void insertWorkExperience(WorkExperienceDO workExperience);

    void updateWorkExperience(WorkExperienceDO workExperience);

    WorkExperienceDO findWorkExperienceById(long id);

    List<WorkExperienceDO> findWorkExperiencesByResumeId(long resumeId);

    // 根据需要定义其他查询方法
}
