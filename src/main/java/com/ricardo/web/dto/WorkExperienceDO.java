package com.ricardo.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkExperienceDO {
    private long id;
    private long resumeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String company;
    private String title;
    private String content;
}
