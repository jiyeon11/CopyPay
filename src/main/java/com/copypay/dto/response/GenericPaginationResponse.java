package com.copypay.dto.response;

import com.copypay.dto.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GenericPaginationResponse<T> {
    private final List<T> list;  //조회한 데이터 리스트
    private final Pagination pagination;  //페이지네이션 정보
