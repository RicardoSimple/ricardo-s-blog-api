package com.ricardo.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationExperienceDO {
    private long id;
    private long resumeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String college;
    private String major;
    private String level;
}
