package com.copypay.dto.request;

import jakarta.validation.Valid;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfosRequest {
    @Valid
    private ContractRequest contractRequest;
    @Valid
    private SettlementInfoRequest settlementInfoRequest;
    @Valid
    private PaymentMethodRequest paymentMethodRequest;
    @Valid
    private MemoRequest memoRequest;
}
