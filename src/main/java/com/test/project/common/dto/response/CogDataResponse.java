package com.test.project.common.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CogDataResponse {

    public String fileName;

    public int width;

    public int height;

    public int bandCount;

    public int blockSize;

    public String compressType;

    public String createdAt;
}
