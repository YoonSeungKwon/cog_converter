package com.test.project.service.storage;

import com.test.project.service.domain.ConvertibleImage;

public interface StorageManager {
    Object loadFiles();

    ConvertibleImage loadFile(String key);
}
