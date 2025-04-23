package com.test.project.service.storage;

import com.test.project.service.domain.ConvertibleImage;

import java.util.List;

public interface StorageManager {

    Object load();

    ConvertibleImage load(String key);

    void upload(ConvertibleImage convertibleImage);

    void upload(List<ConvertibleImage> list);

    void delete(String filename);
}
