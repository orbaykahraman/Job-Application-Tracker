package com.nexcode.jobapplicationtracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequestDto {

    @NotBlank
    @Size(max = 140)
    private String companyName;

    @NotBlank
    @Size(max = 140)
    private String position;
}
