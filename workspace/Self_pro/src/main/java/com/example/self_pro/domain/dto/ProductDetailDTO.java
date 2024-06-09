package com.example.self_pro.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
public class ProductDetailDTO {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private String description;

    @Builder
    public ProductDetailDTO(Long id, String name, Double price, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }
}
