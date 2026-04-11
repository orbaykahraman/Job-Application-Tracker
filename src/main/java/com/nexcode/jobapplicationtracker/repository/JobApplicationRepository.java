package com.nexcode.jobapplicationtracker.repository;

import com.nexcode.jobapplicationtracker.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository <JobApplication, UUID > {
}
