package com.test.project.common.exception;

import lombok.Getter;

@Getter
public class ConvertException extends RuntimeException {

    public ConvertException(String message) {
        super(message);
    }

}
