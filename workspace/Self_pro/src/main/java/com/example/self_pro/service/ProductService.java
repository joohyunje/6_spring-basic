package com.example.self_pro.service;

import com.example.self_pro.domain.dto.ProductDTO;
import com.example.self_pro.domain.dto.ProductDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDTO> findAll();

    ProductDetailDTO findDetail(Long id);

}
