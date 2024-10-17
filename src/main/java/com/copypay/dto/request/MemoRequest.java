package com.copypay.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoRequest {
    @NotBlank(message = "MID가 비어있습니다.")
    private String mid;
    private String id;  //사번(아이디)

    @NotBlank(message = "메모 내용을 입력해주세요.")
    private String content;  //내용
}
