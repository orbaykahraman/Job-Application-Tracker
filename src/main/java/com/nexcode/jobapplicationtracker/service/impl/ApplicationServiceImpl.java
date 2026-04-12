package com.nexcode.jobapplicationtracker.service.impl;

import com.nexcode.jobapplicationtracker.domain.JobApplication;
import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import com.nexcode.jobapplicationtracker.dto.request.AddNoteRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.CreateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.UpdateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.response.ApplicationResponseDto;
import com.nexcode.jobapplicationtracker.dto.response.NoteResponseDto;
import com.nexcode.jobapplicationtracker.mapper.ApplicationMapper;
import com.nexcode.jobapplicationtracker.repository.JobApplicationRepository;
import com.nexcode.jobapplicationtracker.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationResponseDto create(CreateApplicationRequestDto createApplicationRequestDto) {
        JobApplication jobApplication = applicationMapper.toEntity(createApplicationRequestDto);
        jobApplicationRepository.save(jobApplication);
        return applicationMapper.toResponse(jobApplication);
    }

    @Override
    public ApplicationResponseDto getById(UUID id) {
        JobApplication jobApplicationById = jobApplicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found job"));
        return applicationMapper.toResponse(jobApplicationById);
    }

    @Override
    public List<ApplicationResponseDto> getAll() {
        List<ApplicationResponseDto> applicationResponseDtoList = jobApplicationRepository.findAll().stream()
                .map(applicationMapper::toResponse)
                .toList();
        return applicationResponseDtoList;
    }

    @Override
    public ApplicationResponseDto update(UUID id, UpdateApplicationRequestDto updateApplicationRequestDto) {

        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found job"));
        jobApplication.setCompanyName(updateApplicationRequestDto.getCompanyName());
        jobApplication.setLastUpdatedAt(LocalDateTime.now());
        jobApplication.setPosition(updateApplicationRequestDto.getPosition());
        jobApplicationRepository.save(jobApplication);

        return applicationMapper.toResponse(jobApplication);
    }

    @Override
    public void delete(UUID id) {
        jobApplicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found job"));
        jobApplicationRepository.deleteById(id);
    }

    @Override
    public ApplicationResponseDto updateStatus(UUID id, ApplicationStatus status) {
        return null;
    }

    @Override
    public NoteResponseDto addNote(UUID id, AddNoteRequestDto addNoteRequestDto) {
        return null;
    }

    @Override
    public List<NoteResponseDto> getNotesByApplicationId(UUID id) {
        return List.of();
    }
}
