package com.ricardo.web.dto;

import com.ricardo.web.model.Team;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamDO {

    private long id;
    private String name;

    private String alias;

    private String industry;

    private String logo;

    private String link;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public Team toTeam(){
        Team team = new Team();
        team.setId(this.id);
        team.setName(this.name);
        team.setAlias(this.alias);
        team.setIndustry(this.industry);
        team.setLogo(this.logo);
        team.setLink(this.link);
        return team;
    }
}
