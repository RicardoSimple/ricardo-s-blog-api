package com.ricardo.web.dao;

import com.ricardo.web.dto.TeamDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeamDAO {

    void insertTeam(TeamDO team);

    void updateTeam(TeamDO team);

    TeamDO findTeamById(long id);

    List<TeamDO> findAllTeams();

    void deleteTeam(long id);

    TeamDO findTeamByName(String name);
}
