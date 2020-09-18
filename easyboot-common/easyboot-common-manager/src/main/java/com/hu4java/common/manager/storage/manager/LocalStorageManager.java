package com.hu4java.common.manager.storage.manager;

import com.hu4java.common.manager.storage.StorageManager;
import com.hu4java.common.manager.storage.StorageProperties;

/**
 * 本地存储
 * @author chenzhenhu
 */
public class LocalStorageManager implements StorageManager {

    private StorageProperties properties;

    public LocalStorageManager(StorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public String uploadToken() {
        return null;
    }

    @Override
    public String uploadToken(String key) {
        return null;
    }

    @Override
    public boolean remove(String key) {
        return false;
    }
}
