package com.ricardo.web.service;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.TeamJobParam;

public interface TeamJobService {
    Result addOrUpdateTeamJob(TeamJobParam param);

    Result getTeamJobByTeamId(String teamId);

    Result getAllTeamJob();

    Result getTeamJobByTeamName(String teamname);

    Result getTeamJobById(String id);

    Result deleteTeamJobById(String id);
}
