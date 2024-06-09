package com.example.self_pro.mapper;

import com.example.self_pro.domain.dto.ProductDTO;
import com.example.self_pro.domain.dto.ProductDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

//    물품 목록 가져오기
    List<ProductDTO> selectList();

//    물품 상세정보
    ProductDetailDTO selectDetail(Long id);
}
