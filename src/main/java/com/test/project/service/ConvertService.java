package com.test.project.service;

import com.test.project.common.dto.request.ConvertAllRequest;
import com.test.project.common.dto.request.ConvertRequest;
import com.test.project.common.dto.response.CogDataResponse;
import com.test.project.common.dto.response.TifDataResponse;
import com.test.project.common.enums.CompressType;
import com.test.project.common.enums.FileFormat;
import com.test.project.common.exception.ConvertException;
import com.test.project.infrastructure.persist.CogImageData;
import com.test.project.service.converter.ConverterFactory;
import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.repository.CogDataRepository;
import com.test.project.service.storage.StorageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ConvertService {

    private final ForkJoinPool executor;

    private final StorageManager storageManager;

    private final CogDataRepository cogDataRepository;

    private final ConverterFactory converterFactory;

    private final int MIN_BLOCK_SIZE = 128;

    private final int DEFAULT_BLOCK_SIZE = 512;

    private final int MAX_BLOCK_SIZE = 1024;

    private CogDataResponse toResponse(CogImageData data){
        return new CogDataResponse(data.getImageName(), data.getWidth(), data.getHeight(), data.getBandCount(),
                data.getBlockSize(), data.getCompressType(), data.getCreatedAt().toString());
    }

    @Transactional
    private CogImageData persistData(CogImageData cogImageData){
        return cogDataRepository.save(cogImageData);
    }

    public List<TifDataResponse> loadFiles(){
        return (List<TifDataResponse>) storageManager.load();
    }

    public CogDataResponse convert(String key, int blockSize, CompressType compressType, FileFormat from, FileFormat to){
        ConvertibleImage convertible = storageManager.load(key);

        convertible.setName(key);
        convertible.setBlockSize(blockSize);
        convertible.setCompressType(compressType);

        ConvertibleImage converted = converterFactory.makeConverter(from, to)
                .convert(convertible);

        storageManager.upload(converted);

        CogImageData cogImageData;

        try {
            cogImageData = persistData(CogImageData.toPersist(converted));
        }catch (Exception e){
            storageManager.delete(converted.getName() + "." + to.getFormat());
            converted.free();
            throw new ConvertException("변환 데이터를 저장하는 중 오류가 발생 :" + e.getMessage());
        }

        converted.free();
        return toResponse(cogImageData);
    }

    public CogDataResponse convertFile(ConvertRequest dto, FileFormat from, FileFormat to){
        Integer blockSize = dto.blockSize;
        if (blockSize == null || blockSize < MIN_BLOCK_SIZE || blockSize > MAX_BLOCK_SIZE)
            blockSize = DEFAULT_BLOCK_SIZE;

        return convert(dto.key, blockSize, CompressType.fromKey(dto.compressType), from, to);
    }

    public List<CogDataResponse> convertAll(ConvertAllRequest dto, FileFormat from, FileFormat to) throws ExecutionException, InterruptedException {
        CompressType compressType = CompressType.fromKey(dto.compressType);
        List<Callable<CogDataResponse>> tasks = new ArrayList<>();

        for(String key : dto.key){
            tasks.add(()->{
                try {
                    Integer blockSize = dto.blockSize;
                    if (blockSize == null || blockSize < MIN_BLOCK_SIZE || blockSize > MAX_BLOCK_SIZE)
                        blockSize = DEFAULT_BLOCK_SIZE;

                    return convert(key, blockSize, compressType, from, to);
                }catch (Exception e){
                    System.out.println(key + " 변환 실패");
                    return null;
                }
            });
        }

        List<Future<CogDataResponse>> result = executor.invokeAll(tasks);
        List<CogDataResponse> list = new ArrayList<>();

        for (Future<CogDataResponse> future : result) {
            CogDataResponse response = future.get();
            if (response != null) {
                list.add(response);
            }
        }

        return list;
    }

}
