package com.copypay.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequest {
    private int firstIndex;  //페이징 sql의 조건절에 사용되는 시작 rownum
    private int pageSize;  //페이지에 표시될 리스트 수
}
