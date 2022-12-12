package com.sunil.userservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DuplicateResourceFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public DuplicateResourceFoundException(String resourceName, String fieldName, String fieldValue) {
        super(resourceName + " already present with " + fieldName + " : " + fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public DuplicateResourceFoundException(String message) {
        super(message);
    }

    public DuplicateResourceFoundException() {
        super("Resource is already present on server");
    }
}
