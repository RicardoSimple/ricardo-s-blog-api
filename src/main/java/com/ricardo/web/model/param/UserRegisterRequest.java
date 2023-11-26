package com.ricardo.web.model.param;

import com.ricardo.web.dto.TalentUserDO;
import com.ricardo.web.dto.TeamUserDO;
import lombok.Data;

@Data
public class UserRegisterRequest {
    // 用户注册参数
    private String id;
    private String userType;

    private String name;

    private String nickName;

    private String phone;

    private String avatar;

    private String email;

    private String pwd;

    private String status;

    private String education;

    private String teamId;

    private String role;

    public TalentUserDO toTalentUserDO(){
        TalentUserDO talentUserDO = new TalentUserDO();
        talentUserDO.setId(Long.parseLong(this.id));
        talentUserDO.setName(this.name);
        talentUserDO.setNickName(this.nickName);
        talentUserDO.setPhone(this.phone);
        talentUserDO.setAvatar(this.avatar);
        talentUserDO.setEmail(this.email);
        talentUserDO.setStatus(this.status);
        talentUserDO.setEducation(this.education);

        return talentUserDO;
    }

    public TeamUserDO toTeamUserDO(){
        TeamUserDO teamUserDO = new TeamUserDO();

        teamUserDO.setId(Long.parseLong(this.id));
        teamUserDO.setName(this.name);
        teamUserDO.setNickName(this.nickName);
        teamUserDO.setPhone(this.phone);
        teamUserDO.setAvatar(this.avatar);
        teamUserDO.setEmail(this.email);

        teamUserDO.setRole(this.role);

        return teamUserDO;
    }
}
