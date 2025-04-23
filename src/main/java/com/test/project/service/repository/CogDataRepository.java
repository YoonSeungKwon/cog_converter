package com.test.project.service.repository;

import com.test.project.infrastructure.persist.CogImageData;

import java.util.List;

public interface CogDataRepository {

    CogImageData save(CogImageData cogImageData);

    List<CogImageData> findDataByWidth(int width);

    List<CogImageData> findDataByHeight(int height);

    List<CogImageData> findDataByBandCount(int bandCount);

    List<CogImageData> findDataByWidthAndHeight(int width, int height);

    List<CogImageData> findDataByCompressType(String compressType);

    List<CogImageData> findAllDataLatest();

}
