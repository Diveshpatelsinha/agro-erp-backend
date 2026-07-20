package com.agroerp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a requested record does not exist in the database.
 *
 * Examples:
 *  - GET /api/products/999  → product 999 does not exist
 *  - GET /api/customers/50  → customer 50 does not exist
 *
 * Automatically returns HTTP 404 Not Found.
 *
 * Usage in service layer:
 *  throw new ResourceNotFoundException("Product", "id", 999);
 *
 * Produces message:
 *  "Product not found with id : '999'"
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String resourceName,
                                     String fieldName,
                                     Object fieldValue) {
        super(String.format("%s not found with %s : '%s'",
                resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}