package com.ricardo.web.model;

import lombok.Data;

import java.time.LocalDate;

// 工作经历
@Data
public class WorkExperience {
    private LocalDate start;
    private LocalDate end;

    private String company;

    private String title;

    private String content;
}
