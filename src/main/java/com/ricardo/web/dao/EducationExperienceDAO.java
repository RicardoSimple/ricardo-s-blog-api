package com.ricardo.web.dao;

import com.ricardo.web.dto.EducationExperienceDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EducationExperienceDAO {
    void insertEducationExperience(EducationExperienceDO educationExperience);

    void updateEducationExperience(EducationExperienceDO educationExperience);

    EducationExperienceDO findEducationExperienceById(long id);

    List<EducationExperienceDO> findEducationExperiencesByResumeId(long resumeId);

    // 根据需要定义其他查询方法
}
