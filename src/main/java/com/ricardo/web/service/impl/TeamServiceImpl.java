package com.ricardo.web.service.impl;

import com.ricardo.web.dao.TeamDAO;
import com.ricardo.web.dto.CommentDO;
import com.ricardo.web.dto.TeamDO;
import com.ricardo.web.model.Comment;
import com.ricardo.web.model.Result;
import com.ricardo.web.model.Team;
import com.ricardo.web.model.param.TeamRequestParam;
import com.ricardo.web.service.TeamService;
import com.ricardo.web.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDAO teamDAO;
    @Override
    public Result addTeam(TeamRequestParam param) {
        // 1. 查重
        TeamDO teamDO = teamDAO.findTeamByName(param.getName());
        if(teamDO!=null){
            return Result.fail(Code.FAIL_DUPLICATE,"已有该企业");
        }
        // 2. 入库
        teamDO = new TeamDO();
        teamDO.setName(param.getName());
        teamDO.setAlias(param.getAlias());
        teamDO.setLink(param.getLink());
        teamDO.setLogo(param.getLogo());
        teamDO.setIndustry(param.getIndustry());

        teamDAO.insertTeam(teamDO);
        // 3. 返回
        return Result.success(null);
    }

    @Override
    public Result getAllTeam() {
        List<TeamDO> allteam = teamDAO.findAllTeams();
        List<Team> results = new ArrayList<>();
        for (int i = 0; i < allteam.size(); i++) {
            results.add(allteam.get(i).toTeam());
        }
        return Result.success(results);
    }

    @Override
    public Result getTeamById(String id) {
        TeamDO teamDO = teamDAO.findTeamById(Long.valueOf(id));
        if(teamDO==null){
            return Result.fail(Code.FAIL_NO_DATA,"id错误");
        }
        return Result.success(teamDO.toTeam());
    }
}
