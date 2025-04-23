package com.test.project.infrastructure.persist;

import com.test.project.service.domain.ConvertibleImage;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "image_data")
public class CogImageData {

    @Id
    private String imageName;

    private int width;

    private int height;

    private int bandCount;

    private String compressType;

    private int blockSize;

    private LocalDateTime createdAt;

    public static CogImageData toPersist(ConvertibleImage image){
        return CogImageData.builder()
                .imageName(image.getName() + ".tiff")
                .width(image.getWidth())
                .height(image.getHeight())
                .bandCount(image.getBandCount())
                .blockSize(image.getBlockSize())
                .compressType(image.getCompressType().getKey())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
