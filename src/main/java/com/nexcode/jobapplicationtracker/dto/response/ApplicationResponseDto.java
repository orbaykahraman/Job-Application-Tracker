package com.nexcode.jobapplicationtracker.dto.response;

import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ApplicationResponseDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String companyName;
    @NotBlank
    private String position;


    private ApplicationStatus status;

    private LocalDateTime appliedDate;

    private LocalDateTime lastUpdatedAt;
}
