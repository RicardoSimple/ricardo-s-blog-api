package com.ricardo.web.api;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.TeamRequestParam;
import com.ricardo.web.service.TeamService;
import com.ricardo.web.util.Code;
import com.ricardo.web.util.InviteCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamApi {

    @Autowired
    private TeamService teamService;

    @PostMapping("/add")
    public Result addTeam(@RequestBody TeamRequestParam param){
        String code = param.getCode();
        if(code==null||(!code.equals(InviteCode.getInviteCode()))){
            return Result.fail(Code.FAIL_NO_AUTH,"邀请码不正确");
        }
        return teamService.addTeam(param);
    }

    @GetMapping("/all")
    public Result getAllTeam(){
        return teamService.getAllTeam();
    }

    @GetMapping("/get")
    public Result getTeamById(@RequestParam("id")String id){
        return teamService.getTeamById(id);
    }
}
