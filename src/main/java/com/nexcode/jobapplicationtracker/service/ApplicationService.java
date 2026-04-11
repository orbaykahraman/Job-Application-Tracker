package com.nexcode.jobapplicationtracker.service;

import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import com.nexcode.jobapplicationtracker.dto.request.AddNoteRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.CreateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.UpdateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.response.ApplicationResponseDto;
import com.nexcode.jobapplicationtracker.dto.response.NoteResponseDto;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    ApplicationResponseDto create (CreateApplicationRequestDto createApplicationRequestDto);
    ApplicationResponseDto getById (UUID id);
    List<ApplicationResponseDto> getAll ();
    ApplicationResponseDto update(UUID id, UpdateApplicationRequestDto updateApplicationRequestDto);
    void delete (UUID id);
    ApplicationResponseDto updateStatus (UUID id, ApplicationStatus status);
    NoteResponseDto addNote (UUID id, AddNoteRequestDto addNoteRequestDto);
    List<NoteResponseDto> getNotesByApplicationId( UUID id);

}
