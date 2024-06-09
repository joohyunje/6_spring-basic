package com.example.self_pro.controller;

import com.example.self_pro.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String index(){
        return "product/index";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/productList";
    }
    // detail/1
    //
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){

        model.addAttribute("product", productService.findDetail(id));

        return "product/detail";
    }
}
