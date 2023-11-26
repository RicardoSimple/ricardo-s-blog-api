package com.ricardo.web.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectExperience {
    private LocalDate start;
    private LocalDate end;
    private String title;
    private String content;
    private String result;

}
