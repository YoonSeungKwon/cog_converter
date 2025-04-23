package com.test.project.service.converter;

import com.test.project.common.enums.FileFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterFactory {

    private final CogConverter cogConverter;

    public ImageConverter makeConverter(FileFormat from, FileFormat to){
        if((from == FileFormat.TIF || from == FileFormat.TIFF) && to == FileFormat.COG){
            return cogConverter;
        }else{
            throw new RuntimeException();
        }
    }

}
