package com.hu4java.web.common.controller;

import com.hu4java.common.manager.StorageManager;
import com.hu4java.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzhenhu
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired(required = false)
    private StorageManager storageManager;

    @GetMapping("/token")
    public Result<String> token() {
        return Result.success(storageManager.uploadToken());
    }

}
