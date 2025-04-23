package com.test.project.service.storage;

import com.test.project.common.exception.StorageException;
import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.domain.GdalConvertibleImage;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


public abstract class GdalImageLoader{

    @Value("${bucket_name1}")
    private String loadPath;

    @Value("${bucket_name2}")
    private String uploadPath;

    public ConvertibleImage load(String key){
        try {
            String s3Path = "/vsis3/" + loadPath + "/" + key;
            Dataset dataset = gdal.Open(s3Path, gdalconstConstants.GA_ReadOnly);
            return new GdalConvertibleImage(dataset);
        }catch (Exception e){
            e.printStackTrace();
            throw new StorageException("S3에서 불러오는 과정에서 오류 발생 :" + e.getMessage());
        }
    }

    public void upload(ConvertibleImage convertibleImage){
        return;
    }

    public void upload(List<ConvertibleImage> list){
        return;
    }

}
