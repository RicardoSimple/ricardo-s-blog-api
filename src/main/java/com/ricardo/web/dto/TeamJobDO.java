package com.ricardo.web.dto;

import cn.hutool.Hutool;
import com.ricardo.web.model.TeamJob;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamJobDO {
    private long id;

    private String jobName;

    private long teamId;

    private String description;

    private String content;

    private String jobType;

    private String tags;

    private int countNum;
    private int minSalary;
    private int maxSalary;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    public TeamJob toTeamJob(){
        TeamJob teamJob = new TeamJob();
        teamJob.setId(this.id);
        teamJob.setJobName(this.jobName);
        teamJob.setTeamId(this.teamId);
        teamJob.setType(this.jobType);
        teamJob.setDesc(this.description);
        teamJob.setContent(this.content);
        teamJob.setTags(this.tags.split("-"));
        teamJob.setCount(this.countNum);
        teamJob.setSalaryRange(new int[]{this.minSalary,this.maxSalary});
        return teamJob;
    }
}
