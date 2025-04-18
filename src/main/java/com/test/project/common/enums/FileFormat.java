package com.test.project.common.enums;

import lombok.Getter;

@Getter
public enum FileFormat {

    TIF(1),
    COG(2),
    ;

    private final int key;

    FileFormat(int key){
        this.key = key;
    }
}
