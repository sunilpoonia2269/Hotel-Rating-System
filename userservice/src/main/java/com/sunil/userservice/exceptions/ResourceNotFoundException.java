package com.sunil.userservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String resourceField;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String resourceField, String fieldValue) {
        super(resourceName + " Not found with " + resourceField + " : " + fieldValue);
        this.resourceField = resourceField;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Resource Not found on Server");
    }
}
