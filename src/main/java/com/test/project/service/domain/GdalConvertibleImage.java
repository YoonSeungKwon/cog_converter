package com.test.project.service.domain;

import org.gdal.gdal.Dataset;

public class GdalConvertibleImage implements ConvertibleImage{

    public Dataset dataset;

    public GdalConvertibleImage(Dataset dataset){
        this.dataset = dataset;
    }


    @Override
    public long getWidth() {
        return 0;
    }

    @Override
    public long getHeight() {
        return 0;
    }

    @Override
    public long getBandCount() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void free() {
        this.dataset.delete();
    }
}
