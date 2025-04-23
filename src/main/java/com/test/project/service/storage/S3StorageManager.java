package com.test.project.service.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.test.project.common.dto.response.TifDataResponse;
import com.test.project.common.exception.StorageException;
import com.test.project.service.domain.ConvertibleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class S3StorageManager extends GdalImageLoader implements StorageManager{

    private final AmazonS3Client amazonS3Client;

    @Value("${bucket_name1}")
    private String loadPath;

    @Value("${bucket_name2}")
    private String upLoadPath;

    @Value("${folder_path}")
    private String folderPath;

    @Override
    public List<TifDataResponse> load(){
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(loadPath);

        ListObjectsV2Result result;
        List<TifDataResponse> list = new ArrayList<>();

        List<String> temp = new ArrayList<>();

        do {
            result = amazonS3Client.listObjectsV2(request);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                list.add(new TifDataResponse(objectSummary.getKey(), objectSummary.getSize(), objectSummary.getLastModified()));
                temp.add("\"" + objectSummary.getKey() + "\"");
            }
            request.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return list;
    }

    @Override
    public ConvertibleImage load(String key){
        return super.load(key);
    }


    @Override
    public void upload(ConvertibleImage convertibleImage) {
        super.upload(convertibleImage);
    }

    @Override
    public void upload(List<ConvertibleImage> list) {
        super.upload(list);
    }

    @Override
    public void delete(String fileName) {
        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(upLoadPath, folderPath+fileName));
        } catch (Exception e) {
            throw new StorageException(fileName + "삭제 실패 : " + e.getMessage());
        }
    }
}
