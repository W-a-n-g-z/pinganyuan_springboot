package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.model.FileInfo;
import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoFile;
import com.enss.ipfsgate.service.FileService;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {



    @Autowired
    private FileService fileService;

    @RequestMapping("/findall")
    public List<Map<String,Object>> findtest(){
        return fileService.selectAll();
    }

    @RequestMapping("/search")
    public List<FileInfo> search(String repo_name,String file_name,String file_path){
        //PrInfo prInfo = new PrInfo("twf","test","无","未通过","无");
//        String repo_name="test";
//        String file_name="file_1";
//        String file_path="/twf";
        return fileService.search(repo_name,file_name,file_path);
    }

    @RequestMapping("/searchrepo")
    public List<FileInfo> search(String repo_name){
        return fileService.searchrepo(repo_name);
    }

    @RequestMapping("/newfile")
    public int newfile(String repo_name,String file_name,String file_path){
//        FileInfo fileInfo = new FileInfo("test_java","test","/abc");
        FileInfo fileInfo = new FileInfo(repo_name,file_name,file_path);
        return fileService.insert(fileInfo);
    }

    /*
    上传文件
     */
    @ResponseBody
    @RequestMapping("uploadRepoFile")
    public Resp uploadDataFile(@RequestParam String fName, @RequestParam String fSize,@RequestParam Integer repoId,@RequestParam Integer branchId,
                               @RequestParam String relativePath, @RequestParam("uploadFile") MultipartFile multipartFile) {
        //接收上传文件
        log.info("上传的文件名称为:{}", fName);
        String savePath = AppConfigSchedule.fileCachePath;
        File saveFile = new File(savePath + File.separator + fName);
        String fileWholePath = savePath + fName;     //文件保存完整路径
        System.out.println("完整路径：" + fileWholePath);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(saveFile);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
            log.info("------>>>>>>uploaded a file successfully!<<<<<<------");
        } catch (IOException e) {
            return new Resp(1, "文件上传失败！");
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                log.error("fileOutputStream关闭异常", e);
            }
        }
        //插入文件信息
        RepoFile uploadDataFile = new RepoFile();
        uploadDataFile.setRepoId(repoId);
        uploadDataFile.setBranchId(branchId);
        uploadDataFile.setFileName(fName);
        uploadDataFile.setRelativePath(relativePath);
        uploadDataFile.setFileSize(new BigInteger(fSize));
        uploadDataFile.setFileSizeStr(FileUtil.sizeToStr(new BigInteger(fSize)));
        uploadDataFile.setTempSavePath(fileWholePath);
        return fileService.uploadIpfsAndChain(uploadDataFile);
    }

    @RequestMapping("/updateFileInfo")
    public Resp updateFileInfo(@RequestBody RepoFile repoFile){
        System.out.println("repoFile.remark:"+repoFile.getRemark());
        System.out.println("repoFile.id:"+repoFile.getId());
        fileService.updateFileInfo(repoFile);
        return Resp.success("保存成功！");
    }

}