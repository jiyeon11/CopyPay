package com.copypay.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDoneRequest extends PaginationRequest{
    private String searchOption;
    private String searchValue;
}
