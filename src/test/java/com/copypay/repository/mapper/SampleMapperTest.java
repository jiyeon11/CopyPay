package com.copypay.repository.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleMapperTest {
    @Autowired
    SampleMapper sampleMapper;

    @Test
    @DisplayName("getList 호출 성공 테스트")
    void getListSuccessTest (){

        List<Map<String, Object>> list = sampleMapper.getList();

        assertThat(list).isNotEmpty();

    }
}