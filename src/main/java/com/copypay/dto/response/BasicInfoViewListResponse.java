package com.copypay.dto.response;

import com.copypay.dto.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BasicInfoViewListResponse {
    private List<BasicInfoViewResponse> list;
    private Pagination pagination;
}
