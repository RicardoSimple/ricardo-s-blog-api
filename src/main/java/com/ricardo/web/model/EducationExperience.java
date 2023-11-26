package com.ricardo.web.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationExperience {

    private LocalDate start;
    private LocalDate end;
    private String college;
    private String major;
    private String level;

}
