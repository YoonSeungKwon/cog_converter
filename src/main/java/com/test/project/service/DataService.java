package com.test.project.service;

import com.test.project.common.dto.response.CogDataResponse;
import com.test.project.infrastructure.persist.CogImageData;
import com.test.project.service.repository.CogDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final CogDataRepository cogDataRepository;

    private List<CogDataResponse> toResponse(List<CogImageData> list){

        List<CogDataResponse> result = new ArrayList<>();

        for(CogImageData data : list){
            result.add(new CogDataResponse(data.getImageName(), data.getWidth(), data.getHeight(), data.getBandCount(),
                    data.getBlockSize(), data.getCompressType(), data.getCreatedAt().toString()));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<CogDataResponse> find(Integer width, Integer height, Integer bandCount, String compressType){

        if(width != null && height != null){
            return toResponse(cogDataRepository.findDataByWidthAndHeight(width, height));
        }else if(width != null){
            return toResponse(cogDataRepository.findDataByWidth(width));
        }else if(height != null){
            return toResponse(cogDataRepository.findDataByHeight(height));
        }else if(bandCount != null){
            return toResponse(cogDataRepository.findDataByBandCount(bandCount));
        }else if(compressType != null){
            return toResponse(cogDataRepository.findDataByCompressType(compressType));
        }else{
            return toResponse(cogDataRepository.findAllDataLatest());
        }
    }

}
