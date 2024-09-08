package com.copypay.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleRequest {

    @Max(value = 100, message = "아이디는 100을 넘을 수 없습니다.")
    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 10000, message = "내용은 10000자 이내로 입력해주세요.")
    private String content;

}
