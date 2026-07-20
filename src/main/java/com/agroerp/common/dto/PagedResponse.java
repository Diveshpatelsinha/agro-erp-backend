package com.agroerp.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Generic wrapper for all paginated API responses.
 *
 * Every list endpoint (products, customers, sales etc.)
 * returns this same structure so Angular always knows
 * exactly what shape to expect for pagination.
 *
 * Example response:
 * {
 *   "content": [...],
 *   "page": 0,
 *   "size": 10,
 *   "totalElements": 150,
 *   "totalPages": 15,
 *   "last": false
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {

    // the actual list of records for this page
    private List<T> content;

    // current page number (0-indexed)
    private int page;

    // how many records per page
    private int size;

    // total records in the database
    private long totalElements;

    // total number of pages
    private int totalPages;

    // true if this is the last page
    private boolean last;
}