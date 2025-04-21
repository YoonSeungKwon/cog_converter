package com.test.project.common.enums;

import lombok.Getter;

@Getter
public enum CompressType {

    LZW("LZW"),
    ;

    private final String key;

    CompressType(String key){
        this.key = key;
    }

}
