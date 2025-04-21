package com.test.project.service.domain;

import com.test.project.common.enums.FileFormat;

public interface ConvertibleImage {

    long getWidth();

    long getHeight();

    long getBandCount();

    String getName();

    void free();
}
