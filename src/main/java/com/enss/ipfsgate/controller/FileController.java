package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.FileInfo;
import com.enss.ipfsgate.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/findall")
    public List<Map<String,Object>> findtest(){
        return fileService.selectAll();
    }

    @RequestMapping("/search")
    public List<FileInfo> search(){
        //PrInfo prInfo = new PrInfo("twf","test","无","未通过","无");
        String repo_name="test";
        String file_name="file_1";
        String file_path="/twf";
        return fileService.search(repo_name,file_name,file_path);
    }

    @RequestMapping("/newfile")
    public int newfile(){
        FileInfo fileInfo = new FileInfo("test_java","test","/abc");
        return fileService.insert(fileInfo);
    }
}