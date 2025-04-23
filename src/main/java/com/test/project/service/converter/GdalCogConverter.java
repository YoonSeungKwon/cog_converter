package com.test.project.service.converter;

import com.test.project.common.enums.CompressType;
import com.test.project.common.enums.FileFormat;
import com.test.project.common.exception.ConvertException;
import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.domain.GdalConvertibleImage;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.TranslateOptions;
import org.gdal.gdal.gdal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GdalCogConverter {

    @Value("${bucket_name2}")
    private String uploadPath;

    @Value("${folder_path}")
    private String folderPath;

    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

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
        try {
            GdalConvertibleImage gdalConvertibleImage = (GdalConvertibleImage) convertibleImage;

            int seq = map.compute(convertibleImage.getName(), (k, v) -> v == null ? 1 : v + 1);
            String temp = "/vsis3/" + uploadPath + "/" + folderPath + convertibleImage.getName() + "_to_cog_" + seq + "." + format.getFormat();

            Vector<String> translateOptions = new Vector<>();
            translateOptions.add("-of");
            translateOptions.add(format.getKey());
            translateOptions.add("-co");
            translateOptions.add("COMPRESS=" + compress.getKey());
            translateOptions.add("-co");
            translateOptions.add("BLOCKSIZE=" + blockSize);

            Dataset dataset = gdal.Translate(temp, gdalConvertibleImage.dataset, new TranslateOptions(translateOptions));


            ConvertibleImage converted = new GdalConvertibleImage(dataset);
            converted.setName(convertibleImage.getName() + "-to-cog-" + seq + "." + format.getFormat());
            converted.setBlockSize(blockSize);
            converted.setCompressType(compress);

            return converted;
        }catch (Exception e){
            throw new ConvertException("COG 변환 과정중 오류 발생 :" + e.getMessage());
        }
    }

}
