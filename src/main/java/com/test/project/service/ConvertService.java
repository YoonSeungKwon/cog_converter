package com.test.project.service;

import com.test.project.service.storage.StorageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvertService {

    private final StorageManager storageManager;

    //Load all files (Be Converted)
    public Object loadFiles(){
        return storageManager.loadFiles();
    }

    //Load file
    public Object loadFile(String name){

    }

    //Convert Tif file to COG file

    //Convert other files to COG files

    //Convert Tif files to COG files

    //Convert others to COG files

    //Get Tif files using oo

    //Get Tif files using oo

    //Get Tif files using oo

    //Get Tif files using oo

}
