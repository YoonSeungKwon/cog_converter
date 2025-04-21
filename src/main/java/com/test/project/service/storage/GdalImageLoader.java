package com.test.project.service.storage;

import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.domain.GdalConvertibleImage;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;
import org.springframework.beans.factory.annotation.Value;

public abstract class GdalImageLoader {

    @Value("${bucket_name1}")
    private String bucket;


    public ConvertibleImage load(String key){
        String s3Path = "/vsis3/" + bucket + "/" + key;
        Dataset dataset = gdal.Open(s3Path, gdalconstConstants.GA_ReadOnly);
        return  new GdalConvertibleImage(dataset);
    }

}
