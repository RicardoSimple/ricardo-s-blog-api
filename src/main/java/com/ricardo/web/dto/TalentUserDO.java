package com.ricardo.web.dto;

import com.ricardo.web.model.TalentUser;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TalentUserDO {
    private long id;

    private String name;

    private String phone;

    private String nickName;

    private String avatar;

    private String email;

    private String pwd;

    private String status;

    private String education;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public TalentUser toUser(){
        TalentUser talentUser = new TalentUser();
        talentUser.setEducation(this.education);
        talentUser.setStatus(this.status);
        talentUser.setId(this.id);
        talentUser.setName(this.name);
        talentUser.setNickName(this.nickName);
        talentUser.setEmail(this.email);
        talentUser.setAvatar(this.avatar);
        talentUser.setPhone(this.phone);
        return talentUser;
    }
}
