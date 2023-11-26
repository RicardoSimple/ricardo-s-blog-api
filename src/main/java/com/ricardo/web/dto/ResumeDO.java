package com.ricardo.web.dto;

import com.ricardo.web.model.Achievement;
import com.ricardo.web.model.EducationExperience;
import com.ricardo.web.model.ProjectExperience;
import com.ricardo.web.model.WorkExperience;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDO {
    private long id;
    private long userId;
    private String status;
    private String description;
    private RequirementDO requirement;
    private List<WorkExperienceDO> workExperiences;
    private List<ProjectExperienceDO> projectExperiences;
    private List<EducationExperienceDO> educationExperiences;
    private List<AchievementDO> achievements;
}

