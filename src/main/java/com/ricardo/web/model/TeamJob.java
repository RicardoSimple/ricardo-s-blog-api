package com.ricardo.web.model;

import lombok.Data;

@Data
public class TeamJob {
    private long id;

    private String jobName;

    private long teamId;

    private String desc;

    private String content;

    private String type;

    private String[] tags;
    private int[] salaryRange;

    private int count;

}
