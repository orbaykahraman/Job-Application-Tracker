package com.nexcode.jobapplicationtracker.mapper;

import com.nexcode.jobapplicationtracker.domain.ApplicationNote;
import com.nexcode.jobapplicationtracker.domain.JobApplication;
import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import com.nexcode.jobapplicationtracker.dto.request.CreateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.response.ApplicationResponseDto;
import com.nexcode.jobapplicationtracker.dto.response.NoteResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApplicationMapper {

    public JobApplication toEntity (CreateApplicationRequestDto createApplicationRequestDto){

        if (createApplicationRequestDto == null){
            throw new RuntimeException("CreateApplicationRequest is null");
        }

        JobApplication jobApplication = JobApplication.builder()
                .companyName(createApplicationRequestDto.getCompanyName())
                .position(createApplicationRequestDto.getPosition())
                .appliedDate(LocalDateTime.now())
                .lastUpdatedAt(LocalDateTime.now())
                .status(ApplicationStatus.APPLIED)
                .build();
        return jobApplication;
    }

    public ApplicationResponseDto toResponse (JobApplication jobApplication){
        if (jobApplication == null){
            throw new RuntimeException("JobApplication is null");
        }

        ApplicationResponseDto responseDto = ApplicationResponseDto.builder()
                .id(jobApplication.getId())
                .status(jobApplication.getStatus())
                .position(jobApplication.getPosition())
                .companyName(jobApplication.getCompanyName())
                .appliedDate(jobApplication.getAppliedDate())
                .lastUpdatedAt(jobApplication.getLastUpdatedAt())
                .build();
        return responseDto;
    }

    public NoteResponseDto toNoteResponse (ApplicationNote applicationNote){
        if (applicationNote == null){
            throw new RuntimeException("ApplicationNote is null");
        }

            NoteResponseDto noteResponseDto = NoteResponseDto.builder()
                    .id(applicationNote.getId())
                    .content(applicationNote.getContent())
                    .createdAt(applicationNote.getCreatedAt())
                    .build();
            return noteResponseDto;
        }
    }
