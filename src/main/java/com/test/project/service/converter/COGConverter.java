package com.test.project.service.converter;

import com.test.project.common.enums.FileFormat;
import com.test.project.service.domain.ConvertibleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class COGConverter implements ImageConverter {

    private final GdalConverter gdalConverter;

    @Override
    public ConvertibleImage convert(ConvertibleImage convertible) {
        return gdalConverter.convert(convertible, FileFormat.COG);
    }

}
