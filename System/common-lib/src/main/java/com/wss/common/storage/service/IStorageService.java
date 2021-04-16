package com.wss.common.storage.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface IStorageService {

    void init();

    void store(MultipartFile file, String path);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    String getPath(String path);

    void createFile(String path);

    void deleteAll();

    String getLocation();


}
