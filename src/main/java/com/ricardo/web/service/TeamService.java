package com.ricardo.web.service;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.TeamRequestParam;

public interface TeamService {

    Result addTeam(TeamRequestParam param);

    Result getAllTeam();

    Result getTeamById(String id);
}
