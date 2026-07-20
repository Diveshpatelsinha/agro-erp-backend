package com.agroerp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a business rule is violated.
 *
 * Examples:
 *  - Selling more stock than available inventory
 *  - Registering a duplicate username
 *  - Creating a sale with zero items
 *  - Paying more than the invoice amount
 *
 * Automatically returns HTTP 400 Bad Request.
 *
 * Usage in service layer:
 *  throw new BusinessException("Insufficient stock for product: Urea");
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}