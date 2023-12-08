package com.ricardo.blog.tasks;

import com.ricardo.blog.dao.TagDAO;
import com.ricardo.blog.dto.TagDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

// 保持连接
@Component
@EnableScheduling
public class HeartBeatTask {
    public static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatTask.class);
    @Autowired
    private TagDAO tagDAO;

    @Scheduled(fixedRate = 60000) // 每隔一分钟执行一次
    public void sendHeartbeat() {
        // 在这里执行查询语句，可以是一个简单的 SELECT 语句
        List<TagDO> allTag = tagDAO.findAllTag();
        LOGGER.info("heart beat:"+allTag.size());
    }
}
