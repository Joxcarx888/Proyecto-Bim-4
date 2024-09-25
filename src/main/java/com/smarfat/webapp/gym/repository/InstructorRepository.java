package com.smarfat.webapp.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarfat.webapp.gym.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}

