package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.PrInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PrMapper {
    //新建
    int insert(PrInfo record);
    //查询某一pr
    List<PrInfo> search(@Param("repo_name") String repo_name, @Param("member_name") String member_name);
    //查询某一仓库的所有pr
    List<PrInfo> searchRepo(@Param("repo_name") String repo_name);
    //允许合并
    void agree(@Param("repo_name") String repo_name, @Param("member_name") String member_name);

    //某人提交的pr
    List<Map<String,Object>> onesPr(@Param("member_name") String member_name);

    //某人审核的pr
    List<Map<String,Object>> managePr(@Param("member_name") String member_name);
}
