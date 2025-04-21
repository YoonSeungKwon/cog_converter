package com.test.project.service.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.test.project.service.domain.ConvertibleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class S3StorageManager extends GdalImageLoader implements StorageManager{

    private final AmazonS3Client amazonS3Client;

    @Value("${bucket_name1}")
    private String bucket1;

    @Value("${bucket_name2}")
    private String bucket2;


    public ListObjectsV2Result loadFiles(){
        ListObjectsV2Request req = new ListObjectsV2Request()
                .withBucketName(bucket1);

        ListObjectsV2Result result;

        do {
            result = amazonS3Client.listObjectsV2(req);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                System.out.println("파일: " + objectSummary.getKey() +
                        " (크기: " + objectSummary.getSize() + " bytes)");
            }

            req.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return result;
    }

    public ConvertibleImage loadFile(String key){
        return super.load(key);
    }


}
