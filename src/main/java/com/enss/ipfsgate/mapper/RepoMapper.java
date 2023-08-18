package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.model.repo.vo.RepoBranchVo;
import com.enss.ipfsgate.model.repo.vo.RepoInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RepoMapper {
    //查询
    List<RepoInfoVo> search(RepoInfoVo repoInfoVo);
    Integer searchCount(RepoInfoVo repoInfoVo);
    List<RepoInfoVo> searchDetail(RepoInfoVo repoInfoVo);

    //登录
    List<Map<String,Object>> selectAll();

    //新建
    int insert(RepoInfo record);

    //活跃仓库
    List<Map<String,Object>> activeRepo();

    //某人拥有的仓库
    List<Map<String,Object>> onesRepo(@Param("user_name") String user_name);

    //某人参与的仓库
    List<Map<String,Object>> someoneInRepo(@Param("member_name") String member_name);

    //某个仓库的分支列表
    public List<RepoBranchVo> selectBranchList(int repoId);

    public int addBranch(RepoBranch repoBranch);

    int addDepo(RepoInfo repoInfo);

    int applyForAudit(int repoId);
}
