package com.test.project.service.converter;

import com.test.project.common.enums.FileFormat;
import com.test.project.service.domain.ConvertibleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CogConverter implements ImageConverter {

    private final GdalCogConverter gdalCogConverter;

    @Override
    public ConvertibleImage convert(ConvertibleImage convertible) {
        return gdalCogConverter.convert(convertible, FileFormat.COG, convertible.getCompressType(), convertible.getBlockSize());
    }

}
