package com.nexcode.jobapplicationtracker.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class NoteResponseDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String content;

    private LocalDateTime createdAt;
}
