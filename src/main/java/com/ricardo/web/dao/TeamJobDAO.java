package com.ricardo.web.dao;

import com.ricardo.web.dto.TeamJobDO;
import com.ricardo.web.model.TeamJob;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeamJobDAO {

    void insertTeamJob(TeamJobDO teamJob);

    void updateTeamJob(TeamJobDO teamJob);

    TeamJobDO findTeamJobById(long id);

    List<TeamJobDO> findTeamJobsByTeamId(long teamId);

    void deleteTeamJob(long id);

    List<TeamJobDO> findTeamJobByName(String name);

    List<TeamJobDO> findAllTeamJob();
}
