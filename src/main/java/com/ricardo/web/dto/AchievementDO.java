package com.ricardo.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AchievementDO {
    private long id;
    private long resumeId;
    private String title;
    private String content;
    private String description;
    private LocalDate time;
}

