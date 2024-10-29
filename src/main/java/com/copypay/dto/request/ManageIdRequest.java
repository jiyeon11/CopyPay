package com.copypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManageIdRequest extends  PaginationRequest{
    private String searchOption;
    private String searchValue;
}
