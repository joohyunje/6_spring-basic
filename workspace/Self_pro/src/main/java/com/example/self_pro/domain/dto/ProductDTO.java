package com.example.self_pro.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDTO {

    private Long id;
    private String name;

}