package com.ricardo.web.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Achievement {
    private String title;

    private String content;

    private String desc;

    private LocalDate time;
}
