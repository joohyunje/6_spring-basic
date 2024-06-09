package com.example.self_pro.service;

import com.example.self_pro.domain.dto.ProductDTO;
import com.example.self_pro.domain.dto.ProductDetailDTO;
import com.example.self_pro.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> findAll() {
        return productMapper.selectList();
    }

    @Override
    public ProductDetailDTO findDetail(Long id) {
        return productMapper.selectDetail(id);
    }
}
