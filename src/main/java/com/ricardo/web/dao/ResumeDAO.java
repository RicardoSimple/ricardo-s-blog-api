package com.ricardo.web.dao;

import com.ricardo.web.dto.ResumeDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ResumeDAO {
    void insertResume(ResumeDO resume);

    void updateResume(ResumeDO resume);

    ResumeDO findResumeById(long id);

    List<ResumeDO> findAllResumes(); // 根据需要定义查询方法
}
