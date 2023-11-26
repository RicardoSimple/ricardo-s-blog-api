package com.ricardo.web.api;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.TeamJobParam;
import com.ricardo.web.service.TeamJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teamjob")
public class TeamJobApi {

    @Autowired
    private TeamJobService teamJobService;

    @PostMapping("/add")
    public Result addOrUpdateTeamJob(@RequestBody TeamJobParam param){
        return teamJobService.addOrUpdateTeamJob(param);
    }
    @GetMapping("/getbyteamid")
    public Result  getTeamJobByTeamId(@RequestParam("teamId") String teamId){
        return teamJobService.getTeamJobByTeamId(teamId);
    }

    @GetMapping("/all")
    public Result getAllTeamJob(){
        return teamJobService.getAllTeamJob();
    }
    @GetMapping("/getbyteamname")
    public Result  getTeamJobByTeamName(@RequestParam("teamName") String teamname){
        return teamJobService.getTeamJobByTeamName(teamname);
    }

    @GetMapping("/get")
    public Result getTeamJobById(@RequestParam("id") String Id){
        return teamJobService.getTeamJobById(Id);
    }

    @DeleteMapping("/delete")
    public Result deleteTeamJobById(@RequestParam("id")String id){
        return teamJobService.deleteTeamJobById(id);
    }

}
