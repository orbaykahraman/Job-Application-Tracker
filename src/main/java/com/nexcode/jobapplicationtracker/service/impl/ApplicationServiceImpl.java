package com.nexcode.jobapplicationtracker.service.impl;

import com.nexcode.jobapplicationtracker.domain.ApplicationNote;
import com.nexcode.jobapplicationtracker.domain.JobApplication;
import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import com.nexcode.jobapplicationtracker.dto.request.AddNoteRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.CreateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.UpdateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.response.ApplicationResponseDto;
import com.nexcode.jobapplicationtracker.dto.response.NoteResponseDto;
import com.nexcode.jobapplicationtracker.exception.ApplicationNotFoundException;
import com.nexcode.jobapplicationtracker.exception.InvalidStatusTransitionException;
import com.nexcode.jobapplicationtracker.mapper.ApplicationMapper;
import com.nexcode.jobapplicationtracker.repository.ApplicationNoteRepository;
import com.nexcode.jobapplicationtracker.repository.JobApplicationRepository;
import com.nexcode.jobapplicationtracker.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ApplicationMapper applicationMapper;
    private final Map<ApplicationStatus, List<ApplicationStatus>> VALID_TRANSITIONS;
    private final ApplicationNoteRepository applicationNoteRepository;

    public ApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ApplicationMapper applicationMapper,
                                  ApplicationNoteRepository applicationNoteRepository
    ){
        this.jobApplicationRepository = jobApplicationRepository;
        this.applicationMapper = applicationMapper;
        this.applicationNoteRepository = applicationNoteRepository;

        this.VALID_TRANSITIONS = new HashMap<>();
        this.VALID_TRANSITIONS.put(ApplicationStatus.APPLIED, List.of(ApplicationStatus.SCREENING));
        this.VALID_TRANSITIONS.put(ApplicationStatus.SCREENING, List.of(ApplicationStatus.INTERVIEW));
        this.VALID_TRANSITIONS.put(ApplicationStatus.INTERVIEW, List.of(ApplicationStatus.OFFER, ApplicationStatus.REJECTED));
        this.VALID_TRANSITIONS.put(ApplicationStatus.OFFER, List.of(ApplicationStatus.ACCEPTED));
        this.VALID_TRANSITIONS.put(ApplicationStatus.REJECTED, List.of());
        this.VALID_TRANSITIONS.put(ApplicationStatus.ACCEPTED, List.of());
    }



    @Override
    public ApplicationResponseDto create(CreateApplicationRequestDto createApplicationRequestDto) {
        JobApplication jobApplication = applicationMapper.toEntity(createApplicationRequestDto);
        jobApplicationRepository.save(jobApplication);
        return applicationMapper.toResponse(jobApplication);
    }

    @Override
    public ApplicationResponseDto getById(UUID id) {
        JobApplication jobApplicationById = jobApplicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found with id: " + id));
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
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found with id: " + id));
        jobApplication.setCompanyName(updateApplicationRequestDto.getCompanyName());
        jobApplication.setLastUpdatedAt(LocalDateTime.now());
        jobApplication.setPosition(updateApplicationRequestDto.getPosition());
        jobApplicationRepository.save(jobApplication);

        return applicationMapper.toResponse(jobApplication);
    }

    @Override
    public void delete(UUID id) {
        jobApplicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found with id: " + id));
        jobApplicationRepository.deleteById(id);
    }

    @Override
    public ApplicationResponseDto updateStatus(UUID id, ApplicationStatus status) {
        JobApplication updateJobStatus = jobApplicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found with id: " + id));
        ApplicationStatus currentStatus = updateJobStatus.getStatus();
        List<ApplicationStatus> validNextStatuses = VALID_TRANSITIONS.get(currentStatus);
        if (!validNextStatuses.contains(status)){
            throw new InvalidStatusTransitionException("Invalid status transition:" + currentStatus +
                    " → " + status );
        }
        updateJobStatus.setStatus(status);
        updateJobStatus.setLastUpdatedAt(LocalDateTime.now());
        jobApplicationRepository.save(updateJobStatus);

        return applicationMapper.toResponse(updateJobStatus);
    }

    @Override
    public NoteResponseDto addNote(UUID id, AddNoteRequestDto addNoteRequestDto) {

        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found with id: " + id));
        ApplicationNote applicationNote = ApplicationNote.builder()
                .content(addNoteRequestDto.getContent())
                .createdAt(LocalDateTime.now())
                .jobApplication(jobApplication)
                .build();

        applicationNoteRepository.save(applicationNote);
        return applicationMapper.toNoteResponse(applicationNote);

    }

    @Override
    public List<NoteResponseDto> getNotesByApplicationId(UUID id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found with id: " + id));
        List<ApplicationNote> applicationNoteList = applicationNoteRepository.findByJobApplication_Id(id);
        return applicationNoteList.stream()
                .map(applicationMapper::toNoteResponse)
                .toList();
    }
}
