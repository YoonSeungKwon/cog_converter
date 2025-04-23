package com.test.project.common.dto.response;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class TifDataResponse {

    public String fileName;

    public long size;

    public Date lastModified;

}
