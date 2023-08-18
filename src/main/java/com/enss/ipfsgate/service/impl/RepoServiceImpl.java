package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.OperateMapper;
import com.enss.ipfsgate.mapper.RepoMapper;
import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.model.repo.vo.RepoBranchVo;
import com.enss.ipfsgate.model.repo.vo.RepoInfoVo;
import com.enss.ipfsgate.service.RepoService;
import com.enss.ipfsgate.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepoServiceImpl implements RepoService {

    @Autowired
    private RepoMapper repoMapper;
    @Autowired
    private OperateMapper operateMapper;

    //查找仓库
    @Override
    public List<RepoInfoVo> search(RepoInfoVo repoInfoVo){
        return repoMapper.search(repoInfoVo);
    }
    @Override
    public Integer searchCount(RepoInfoVo repoInfoVo){
        return repoMapper.searchCount(repoInfoVo);
    }
    //单查
    @Override
    public List<RepoInfoVo> searchDetail(RepoInfoVo repoInfoVo) {
        return repoMapper.searchDetail(repoInfoVo);
    }

    //新建仓库
    @Override
    public int newRepo(RepoInfo repoInfo){
        return repoMapper.insert(repoInfo);
    }

    //查询所有仓库
    @Override
    public List<Map<String,Object>> selectAll(){
        return repoMapper.selectAll();
    }

    //活跃仓库
    @Override
    public List<Map<String, Object>> activeRepo() {
        return repoMapper.activeRepo();
    }

    //某人拥有的仓库
    @Override
    public List<Map<String, Object>> onesRepo(String user_name) {
        return repoMapper.onesRepo(user_name);
    }

    //某人参与的仓库
    @Override
    public List<Map<String, Object>> someoneInRepo(String member_name) {
        return repoMapper.someoneInRepo(member_name);
    }

    @Override
    public List<RepoBranchVo> selectBranchList(int repoId){
        return repoMapper.selectBranchList(repoId);
    }

    @Override
    public int addBranch(RepoBranch repoBranch){
        return repoMapper.addBranch(repoBranch);
    }

    @Override
    public int addDepo(RepoInfo repoInfo) {
        repoMapper.addDepo(repoInfo);

        RepoBranch repoBranch = new RepoBranch();
        repoBranch.setRepoId(repoInfo.getrId());
        repoBranch.setBranchName("master");
        repoMapper.addBranch(repoBranch);

        return repoInfo.getrId();
    }

    @Override
    public int applyForAudit(int repoId) {
        return repoMapper.applyForAudit(repoId);
    }
}
