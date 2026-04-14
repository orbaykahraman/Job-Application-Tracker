package com.nexcode.jobapplicationtracker.controller;

import com.nexcode.jobapplicationtracker.domain.enums.ApplicationStatus;
import com.nexcode.jobapplicationtracker.dto.request.AddNoteRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.CreateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.request.UpdateApplicationRequestDto;
import com.nexcode.jobapplicationtracker.dto.response.ApplicationResponseDto;
import com.nexcode.jobapplicationtracker.dto.response.NoteResponseDto;
import com.nexcode.jobapplicationtracker.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;


    @PostMapping
    ResponseEntity<ApplicationResponseDto> create(@RequestBody @Valid
                                                  CreateApplicationRequestDto createApplicationRequestDto){
        return new ResponseEntity<>(applicationService.create(createApplicationRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ApplicationResponseDto>> getAll (){
        return  ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApplicationResponseDto> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(applicationService.getById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<ApplicationResponseDto> update (@PathVariable UUID id, @RequestBody @Valid UpdateApplicationRequestDto
                                                   updateApplicationRequestDto){
        return ResponseEntity.ok(applicationService.update(id, updateApplicationRequestDto));
    }

    @PatchMapping("/{id}/status")
    ResponseEntity<ApplicationResponseDto> updateStatus (@PathVariable UUID id, @Valid @RequestBody
    ApplicationStatus status){
        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }

    @PostMapping("/{id}/notes")
    ResponseEntity<NoteResponseDto> addNote(@PathVariable UUID id, @RequestBody @Valid AddNoteRequestDto
                                            addNoteRequestDto){
        return ResponseEntity.ok(applicationService.addNote(id,addNoteRequestDto));
    }

    @GetMapping("/{id}/notes")
    ResponseEntity<List<NoteResponseDto>> getNotesByApplicationId(@PathVariable UUID id){
        return ResponseEntity.ok(applicationService.getNotesByApplicationId(id));
    }
}
