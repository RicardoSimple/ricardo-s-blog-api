package com.ricardo.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagDO {
    private long id;

    private String name;

    private LocalDateTime gmtModified;

    private LocalDateTime gmtCreated;

}
