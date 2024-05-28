package com.example.b_dependency.dependency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DTest2Test {

    @Autowired
    private DTest2 dTest2;

    @Test
    void test(){
        dTest2.print2();
    }
}