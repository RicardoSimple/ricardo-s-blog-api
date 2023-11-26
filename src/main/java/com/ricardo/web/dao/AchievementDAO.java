package com.ricardo.web.dao;

import com.ricardo.web.dto.AchievementDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AchievementDAO {
    void insertAchievement(AchievementDO achievement);

    void updateAchievement(AchievementDO achievement);

    AchievementDO findAchievementById(long id);

    List<AchievementDO> findAchievementsByResumeId(long resumeId);

    // 根据需要定义其他查询方法
}
