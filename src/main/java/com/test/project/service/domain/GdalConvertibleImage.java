package com.test.project.service.domain;

import com.test.project.common.enums.CompressType;
import org.gdal.gdal.Dataset;

public class GdalConvertibleImage implements ConvertibleImage{

    public Dataset dataset;

    public String name;

    public CompressType compressType;

    public int blockSize;

    public GdalConvertibleImage(Dataset dataset){
        this.dataset = dataset;
    }

    @Override
    public int getWidth() {
        return dataset.GetRasterXSize();
    }

    @Override
    public int getHeight() {
        return dataset.GetRasterYSize();
    }

    @Override
    public int getBandCount() {
        return dataset.GetRasterCount();
    }

    @Override
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override
    public String getName() {
        int dot = this.name.lastIndexOf('.');
        return this.name.substring(0, dot);
    }

    @Override
    public CompressType getCompressType() {
        return this.compressType;
    }

    @Override
    public void free() {
        dataset.FlushCache();
        dataset.delete();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCompressType(CompressType compressType) {
        this.compressType = compressType;
    }

    @Override
    public void setBlockSize(int size) {
        this.blockSize = size;
    }
}
