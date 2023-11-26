package com.ricardo.web.model;

import lombok.Data;

// 求职期望
@Data
public class Requirement {

    private String salaryCircle;

    private String workType;

    private String workSchedule;

    private String industry;

    private int[] salaryRange;


}
