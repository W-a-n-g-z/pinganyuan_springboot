package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.PrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrMapper {
    //新建
    int insert(PrInfo record);
    //查询
    List<PrInfo> search(@Param("repo_name") String repo_name, @Param("member_name") String member_name);
    //允许合并
    void agree(@Param("repo_name") String repo_name, @Param("member_name") String member_name);
}
