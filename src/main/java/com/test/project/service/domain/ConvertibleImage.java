package com.test.project.service.domain;
import com.test.project.common.enums.CompressType;

public interface ConvertibleImage {

    int getWidth();

    int getHeight();

    int getBandCount();

    int getBlockSize();

    String getName();

    CompressType getCompressType();

    void free();

    void setName(String name);

    void setCompressType(CompressType compressType);

    void setBlockSize(int size);


}
