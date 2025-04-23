package com.test.project.common.enums;

import lombok.Getter;

@Getter
public enum CompressType {

    LZW("LZW"),
    NONE("NONE"),
    DEFLATE("DEFLATE"),
    JPEG("JPEG"),
    WEBP("WEBP"),
    ZSTD("ZSTD"),
    PACKBITS("PACKBITS"),
    LZMA("LZMA"),
    ;

    private final String key;

    CompressType(String key){
        this.key = key;
    }

    public static CompressType fromKey(String key) {
        for (CompressType compress : values()) {
            if (compress.getKey().equalsIgnoreCase(key)) {
                return compress;
            }
        }
        return CompressType.LZW;
    }
}
