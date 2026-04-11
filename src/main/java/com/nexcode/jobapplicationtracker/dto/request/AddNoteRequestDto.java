package com.nexcode.jobapplicationtracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddNoteRequestDto {

    @Size(max = 500)
    @NotBlank
    private String content;
}
