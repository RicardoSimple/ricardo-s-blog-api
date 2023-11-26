package com.ricardo.web.model;

import lombok.Data;

// 简历
@Data
public class Resume {

    private long userId;
    private String status;

    private String desc;

    private Requirement requirement;

    private WorkExperience[] workExperiences;

    private ProjectExperience[] projectExperiences;

    private EducationExperience[] educationExperiences;

    private Achievement[] achievements;
}
