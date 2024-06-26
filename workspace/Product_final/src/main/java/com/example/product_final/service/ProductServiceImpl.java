package com.example.product_final.service;

import com.example.product_final.domain.dto.ProductDTO;
import com.example.product_final.domain.dto.ProductDetailDTO;
import com.example.product_final.domain.vo.ProductVO;
import com.example.product_final.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 특정 변수의 생성자를 만들어주는 어노테이션
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // 생성자 주입
    // @Autowired 생략 가능.
    private final ProductMapper productMapper;

//    public ProductServiceImpl(ProductMapper productMapper){
//        this.productMapper = productMapper;
//    }

    // 전체 물품 리스트를 반환하는 select
    @Override
    public List<ProductDTO> findAll() {
        return productMapper.selectList();
    }

    // 상세보기 페이지로 보낼 데이터 select
    @Override
    public ProductDetailDTO findById(Long id) {
        return productMapper.selectOne(id);
    }

    // 새 물품 등록 insert
    @Override
    public int save(ProductVO vo) {
        return productMapper.insert(vo);
    }

    @Override
    public void edit(ProductVO vo) {
        productMapper.update(vo);
    }

    @Override
    public int delete(Long id) {
        return productMapper.delete(id);
    }
}
