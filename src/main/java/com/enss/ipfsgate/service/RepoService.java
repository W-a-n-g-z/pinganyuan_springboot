package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.model.repo.vo.RepoBranchVo;
import com.enss.ipfsgate.model.repo.vo.RepoInfoVo;
import com.enss.ipfsgate.utils.Resp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RepoService {
    //模糊查询
    List<RepoInfoVo> search(RepoInfoVo repoInfoVo);
    Integer searchCount(RepoInfoVo repoInfoVo);
    //单查
    List<RepoInfoVo> searchDetail(RepoInfoVo repoInfoVo);

    //注册账户
    int newRepo(RepoInfo repoInfo);

    //查询所有用户
    List<Map<String,Object>> selectAll();

    //活跃仓库
    List<Map<String,Object>> activeRepo();

    //某人拥有的仓库
    List<Map<String,Object>> onesRepo(String user_name);

    //某人参与的仓库
    List<Map<String,Object>> someoneInRepo(String member_name);

    //某个仓库的分支列表
    public List<RepoBranchVo> selectBranchList(int repoId);

    public int addBranch(RepoBranch repoBranch);

    int addDepo(RepoInfo repoInfo);
}
