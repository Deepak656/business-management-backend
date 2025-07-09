package com.example.lpgmanagement.utils;

import com.example.lpgmanagement.dto.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {

    // Generic success response for any type
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    // Success response with a message and data
    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(ApiResponse.success(message, data));
    }

    // Error response with a message
    public static ResponseEntity<ApiResponse<Void>> error(String message) {
        return ResponseEntity.badRequest().body(ApiResponse.error(message));
    }

    // Generic page response for pagination
    public static <T> ResponseEntity<ApiResponse<PageResponse<T>>> pageResponse(Page<T> page) {
        PageResponse<T> pageResponse = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
        return ResponseEntity.ok(ApiResponse.success(pageResponse));
    }

    @Data
    @AllArgsConstructor
    public static class PageResponse<T> {
        private List<T> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
    }
}
