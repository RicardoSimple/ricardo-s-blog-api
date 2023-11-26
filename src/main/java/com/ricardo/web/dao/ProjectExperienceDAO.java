package com.ricardo.web.dao;

import com.ricardo.web.dto.ProjectExperienceDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProjectExperienceDAO {
    void insertProjectExperience(ProjectExperienceDO projectExperience);

    void updateProjectExperience(ProjectExperienceDO projectExperience);

    ProjectExperienceDO findProjectExperienceById(long id);

    List<ProjectExperienceDO> findProjectExperiencesByResumeId(long resumeId);

    // 根据需要定义其他查询方法
}
