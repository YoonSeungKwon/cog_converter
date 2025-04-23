package com.test.project.infrastructure;

import com.test.project.infrastructure.persist.CogImageData;
import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.repository.CogDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CogDataRepositoryImpl implements CogDataRepository {

    private final CogJpaRepository cogJpaRepository;

    @Override
    public CogImageData save(CogImageData cogImageData) {
        return cogJpaRepository.save(cogImageData);
    }

    @Override
    public List<CogImageData> findDataByWidth(int width) {
        return cogJpaRepository.findAllDataByWidth(width);
    }

    @Override
    public List<CogImageData> findDataByHeight(int height) {
        return cogJpaRepository.findAllDataByHeight(height);
    }

    @Override
    public List<CogImageData> findDataByBandCount(int bandCount) {
        return cogJpaRepository.findAllDataByBandCount(bandCount);
    }

    @Override
    public List<CogImageData> findDataByWidthAndHeight(int width, int height) {
        return cogJpaRepository.findAllDataByWidthAndHeight(width, height);
    }

    @Override
    public List<CogImageData> findDataByCompressType(String compressType) {
        return cogJpaRepository.findAllDataByCompressType(compressType);
    }

    @Override
    public List<CogImageData> findAllDataLatest() {
        return cogJpaRepository.findAllDataLatest();
    }


}
