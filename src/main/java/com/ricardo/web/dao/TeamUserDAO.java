package com.ricardo.web.dao;

import com.ricardo.web.dto.TalentUserDO;
import com.ricardo.web.dto.TeamUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamUserDAO {
    void insertTeamUser(TeamUserDO teamUser);
    void updateTeamUser(TeamUserDO teamUser);
    TeamUserDO findTeamUserById(long id);
    List<TeamUserDO> findTeamUsersByTeamId(String teamId);
    void deleteTeamUser(long id);
    TeamUserDO findTeamUserByPhone(String phone);

    List<TeamUserDO> findAllTeamUsers();
}
