package com.yunsu.fitmotion.common.domain.exception;

/** 자원을 찾지 못했을 때 던지는 예외 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Object id) {
        super(resourceName + " with id [" + id + "] not found");
    }
}

