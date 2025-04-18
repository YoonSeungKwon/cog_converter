package com.test.project.service.converter;

import com.test.project.common.enums.FileFormat;

public class ConverterFactory {

    public static FileConverter makeConverter(FileFormat before, FileFormat after){
        if(before == FileFormat.TIF && after == FileFormat.COG){
            return new Tif2CogConverter();
        }else{
            throw new RuntimeException();
        }
    }

}
