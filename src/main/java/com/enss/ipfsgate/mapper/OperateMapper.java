package com.enss.ipfsgate.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface OperateMapper {
    //新建
    int insert(@Param("repo_name") String repo_name, @Param("member_name") String member_name,
               @Param("operate") String operate);
    //查询最近十条
    List<Map<String,Object>> search(@Param("repo_name") String repo_name);
}
