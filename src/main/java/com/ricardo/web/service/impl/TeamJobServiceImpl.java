package com.ricardo.web.service.impl;

import com.ricardo.web.dao.TeamJobDAO;
import com.ricardo.web.dto.TeamJobDO;
import com.ricardo.web.model.Result;
import com.ricardo.web.model.TeamJob;
import com.ricardo.web.model.param.TeamJobParam;
import com.ricardo.web.service.TeamJobService;
import com.ricardo.web.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamJobServiceImpl implements TeamJobService {

    @Autowired
    private TeamJobDAO teamJobDAO;
    @Override
    public Result addOrUpdateTeamJob(TeamJobParam param) {
        if (param.getTeamId().isBlank()){
            return Result.fail(Code.FAIL_NO_DATA,"team_id为空");
        }
        TeamJobDO teamJobDO = new TeamJobDO();
        teamJobDO.setTeamId(Long.parseLong(param.getTeamId()));
        teamJobDO.setJobName(param.getJobName());
        teamJobDO.setMinSalary(param.getSalaryRange()[0]);
        teamJobDO.setMaxSalary(param.getSalaryRange()[1]);
        teamJobDO.setTags(String.join("-", param.getTags()));
        teamJobDO.setJobType(param.getType());
        teamJobDO.setDescription(param.getDesc());
        teamJobDO.setContent(param.getContent());
        teamJobDO.setCountNum(param.getCount());

        if (param.getId()==null||param.getId().isBlank()) {
            teamJobDAO.insertTeamJob(teamJobDO);
        }else {
            teamJobDO.setId(Long.parseLong(param.getId()));
            teamJobDAO.updateTeamJob(teamJobDO);
        }

        return Result.success(null);
    }

    @Override
    public Result getTeamJobByTeamId(String teamId) {
        Long id = Long.valueOf(teamId);
        if(id<0){
            return Result.fail(Code.FAIL_NO_ZERO,"id小于0");
        }
        List<TeamJobDO> teamJobDOs = teamJobDAO.findTeamJobsByTeamId(id);

        List<TeamJob> results = new ArrayList<>();
        for (int i = 0; i < teamJobDOs.size(); i++) {
            results.add(teamJobDOs.get(i).toTeamJob());
        }
        return Result.success(results);
    }

    @Override
    public Result getTeamJobById(String id) {
        TeamJobDO teamJobDO =teamJobDAO.findTeamJobById(Long.parseLong(id));
        if(teamJobDO==null){
            return Result.fail(Code.FAIL_NO_DATA,"帖子id不存在");
        }
        return Result.success(teamJobDO.toTeamJob());
    }

    @Override
    public Result deleteTeamJobById(String id) {
        teamJobDAO.deleteTeamJob(Long.parseLong(id));
        return Result.success(null);
    }

    @Override
    public Result getTeamJobByTeamName(String teamname) {
        List<TeamJobDO> teamJobDOs = teamJobDAO.findTeamJobByName(teamname);
        if(teamJobDOs==null){
            return Result.fail(Code.FAIL_NO_DATA,"名称不存在");
        }
        List<TeamJob> results = new ArrayList<>();
        for (int i = 0; i < teamJobDOs.size(); i++) {
            results.add(teamJobDOs.get(i).toTeamJob());
        }
        return Result.success(results);
    }



    @Override
    public Result getAllTeamJob() {
        List<TeamJobDO> allTeamJob = teamJobDAO.findAllTeamJob();
        List<TeamJob> results = new ArrayList<>();

        for (TeamJobDO teamJobDO : allTeamJob) {
            results.add(teamJobDO.toTeamJob());
        }
        return Result.success(results);
    }
}
