package com.test.project.service;

import com.test.project.common.dto.FileRequestDto;
import com.test.project.common.enums.FileFormat;
import com.test.project.service.converter.ConverterFactory;
import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.repository.MetaDataRepository;
import com.test.project.service.storage.S3StorageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertService {

    private final S3StorageManager storageManager;

    private final MetaDataRepository metaDataRepository;

    private final ConverterFactory converterFactory;

    //Load all files (Be Converted)
    public Object loadFiles(){
        return storageManager.loadFiles();
    }
    //Convert Tif file to COG file
    public void tifToCog(FileRequestDto dto){
        ConvertibleImage convertible = storageManager.loadFile(dto.key);
        ConvertibleImage converted = converterFactory.makeConverter(FileFormat.valueOf(dto.from), FileFormat.valueOf(dto.to))
                .convert(convertible);

        System.out.println(converted);
        metaDataRepository.save(converted);

        converted.free();
    }

    //Convert other files to COG files

    //Convert Tif files to COG files

    //Convert others to COG files

    //Get Tif files using oo

    //Get Tif files using oo

    //Get Tif files using oo

    //Get Tif files using oo

}
