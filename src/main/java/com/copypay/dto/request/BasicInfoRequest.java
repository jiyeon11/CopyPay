package com.copypay.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoRequest {
    @Valid
    private ContractRequest contractRequest;
    @Valid
    private SettlementInfoRequest settlementInfoRequest;
    @Valid
    private PaymentMethodRequest paymentMethodRequest;
    @Valid
    private MemoRequest memoRequest;
}
