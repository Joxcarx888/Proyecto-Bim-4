package com.smarfat.webapp.gym.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarfat.webapp.gym.model.Instructor;


public interface InstructorRepository extends JpaRepository <Instructor, Long>{ 

}
