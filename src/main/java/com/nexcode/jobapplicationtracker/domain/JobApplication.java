package com.nexcode.jobapplicationtracker.domain;

import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 140)
    private String companyName;

    @Column(length = 140)
    private String position;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime appliedDate;
    private LocalDateTime lastUpdatedAt;

}
