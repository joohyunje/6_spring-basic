package com.example.board.domain.util;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private int totalElements;
    private int startPage;
    private int endPage;

    public PagedResponse(List<T> content, int currentPage, int totalPages, int pageSize, int totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.totalElements = totalElements;

        this.startPage = Math.max(1, currentPage - 5);
        this.endPage = Math.min(totalPages, currentPage + 4);
    }

}
