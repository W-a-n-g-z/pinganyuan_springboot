package com.enss.ipfsgate.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepoMemMapper {

    List<Map<String,Object>> selectAllMember();

    void addMember(@Param("repo_name") String repo_name, @Param("member_name") String member_name);

    void deleteMember(@Param("member_name") String member_name);
}
