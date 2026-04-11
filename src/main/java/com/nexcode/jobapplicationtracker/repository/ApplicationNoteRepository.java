package com.nexcode.jobapplicationtracker.repository;

import com.nexcode.jobapplicationtracker.domain.ApplicationNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationNoteRepository extends JpaRepository<ApplicationNote, UUID > {
}
