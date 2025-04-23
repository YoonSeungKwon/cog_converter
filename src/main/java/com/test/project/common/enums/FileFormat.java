package com.test.project.common.enums;

import lombok.Getter;

@Getter
public enum FileFormat {

    TIF("TIF", "tif"),
    TIFF("TIFF", "tiff"),
    COG("COG", "tiff"),
    ;

    private final String key;
    private final String format;

    FileFormat(String key, String format){
        this.key = key;
        this.format = format;
    }

    public static FileFormat fromKey(String key) {
        for (FileFormat format : values()) {
            if (format.getKey().equalsIgnoreCase(key)) {
                return format;
            }
        }
        throw new IllegalArgumentException();
    }
}
