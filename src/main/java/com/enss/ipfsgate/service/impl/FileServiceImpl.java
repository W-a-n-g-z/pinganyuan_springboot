package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.FileMapper;
import com.enss.ipfsgate.model.FileInfo;
import com.enss.ipfsgate.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileInfo> search(String repo_name, String file_name, String file_path) {
        return fileMapper.search(repo_name, file_name, file_path);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        return fileMapper.selectAll();
    }

    @Override
    public int insert(FileInfo record) {
        return fileMapper.insert(record);
    }
}
