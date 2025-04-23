package com.test.project.common.config;

import jakarta.annotation.PostConstruct;
import org.gdal.gdal.gdal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GdalConfig {

    @Value("${region}")
    private String region;

    @Value("${access_key_id}")
    private String accessKey;

    @Value("${secret_access_key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        gdal.AllRegister();

        gdal.SetConfigOption("AWS_ACCESS_KEY_ID", accessKey);
        gdal.SetConfigOption("AWS_SECRET_ACCESS_KEY", secretKey);
        gdal.SetConfigOption("AWS_REGION", region);
        gdal.SetConfigOption("CPL_VSIL_USE_TEMP_FILE_FOR_RANDOM_WRITE", "YES");
    }

}
