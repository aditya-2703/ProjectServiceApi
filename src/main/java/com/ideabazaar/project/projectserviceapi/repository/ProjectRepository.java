package com.ideabazaar.project.projectserviceapi.repository;

import com.ideabazaar.project.projectserviceapi.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findProjectsByUserId(Long userId);
}
