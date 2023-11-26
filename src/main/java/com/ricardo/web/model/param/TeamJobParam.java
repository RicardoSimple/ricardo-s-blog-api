package com.ricardo.web.model.param;

import lombok.Data;

@Data
public class TeamJobParam {

    private String id;

    private String jobName;

    private String teamId;

    private String desc;

    private String content;

    private String type; //全职 兼职 线上。。

    private int[] salaryRange;
    private String[] tags;

    private int count;
}
