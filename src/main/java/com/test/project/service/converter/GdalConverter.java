package com.test.project.service.converter;

import com.test.project.common.enums.CompressType;
import com.test.project.common.enums.FileFormat;
import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.domain.GdalConvertibleImage;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.TranslateOptions;
import org.gdal.gdal.gdal;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.Vector;

@Component
public class GdalConverter {

    private final CompressType COMPRESS = CompressType.LZW;

    private final int BLOCK_SIZE = 512;

    public ConvertibleImage convert(ConvertibleImage convertibleImage, FileFormat format){
        return convert((GdalConvertibleImage) convertibleImage, format, COMPRESS, BLOCK_SIZE);
    }

    public ConvertibleImage convert(ConvertibleImage convertibleImage, FileFormat format, int blockSize){
        return convert((GdalConvertibleImage) convertibleImage, format, COMPRESS, blockSize);
    }

    public ConvertibleImage convert(ConvertibleImage convertibleImage, FileFormat format, CompressType compress){
        return convert((GdalConvertibleImage) convertibleImage, format, compress, BLOCK_SIZE);
    }

    public ConvertibleImage convert(ConvertibleImage convertibleImage, FileFormat format, CompressType compress, int blockSize){
        UUID uuid = UUID.randomUUID();
        String temp = "/vsimem/"+uuid.toString()+"."+format.getFormat();
        GdalConvertibleImage gdalConvertibleImage = (GdalConvertibleImage) convertibleImage;

        Vector<String> translateOptions = new Vector<>();
        translateOptions.add("-of");
        translateOptions.add(format.getKey());
        translateOptions.add("-co");
        translateOptions.add("COMPRESS="+compress.getKey());
        translateOptions.add("-co");
        translateOptions.add("BLOCKSIZE="+blockSize);

        Dataset dataset = gdal.Translate(temp, gdalConvertibleImage.dataset, new TranslateOptions(translateOptions));

        gdal.Unlink(temp);
        convertibleImage.free();

        return new GdalConvertibleImage(dataset);
    }

}
