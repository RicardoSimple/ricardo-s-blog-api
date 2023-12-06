package com.ricardo.blog.dao;

import com.ricardo.blog.dto.TagDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDAO {
    void insertTag(TagDO tagDO);
    void updateTag(TagDO tagDO);

    List<TagDO> findAllTag();
    TagDO findTagById(long id);
    List<TagDO> findTagsByArticleId(long id);
    TagDO findTagByName(String name);
}
