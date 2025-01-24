package com.ideabazaar.project.projectserviceapi.controller;

import com.ideabazaar.project.projectserviceapi.dto.ProjectDTO;
import com.ideabazaar.project.projectserviceapi.exceptions.ProjectNotFound;
import com.ideabazaar.project.projectserviceapi.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class Project {

    ProjectService projectService;
    Project(ProjectService projectService) {
        this.projectService = projectService;
    }

    // C - Project
    @PostMapping("/project/create")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO ProjectDTO) {
        ProjectDTO Projectdto = this.projectService.createProject(ProjectDTO);
        return new ResponseEntity<>(Projectdto, HttpStatus.CREATED);
    }

    // R - all
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> Projectsdto = this.projectService.getAllProject();
        return new ResponseEntity<>(Projectsdto, HttpStatus.OK);
    }
    // R - single
    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectDTO> getSingleProject(@PathVariable Long id) throws ProjectNotFound {
        ProjectDTO Projectdto = this.projectService.getSingleProject(id);
        if(Projectdto == null) {
            throw new ProjectNotFound(id); // Throw the exception
        }
        return new ResponseEntity<>(Projectdto, HttpStatus.OK);
    }

    // U - update
    @PutMapping("/project/")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO ProjectDTO) throws ProjectNotFound {
        ProjectDTO updatedProjectdto = this.projectService.updateProject(ProjectDTO);
        if(updatedProjectdto == null) {
            throw new ProjectNotFound(ProjectDTO.getId());
        }
        return new ResponseEntity<>(updatedProjectdto, HttpStatus.OK);
    }

    // D - Delete
    @DeleteMapping("/project/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws ProjectNotFound {
        boolean isDeleted = this.projectService.deleteProject(id);

        if (isDeleted) {
            return ResponseEntity.ok("Project  with ID " + id + " has been deleted."); // 200 OK with message
        } else {
            throw new ProjectNotFound(id);
        }
    }




    // Read all the project related to input userid
    @GetMapping("/customproject/{id}")
    public List<ProjectDTO> getAllProjectsDTOByUserId(@PathVariable Long id) throws ProjectNotFound {
        List<ProjectDTO> allprojectData = this.projectService.getProjectlistByUserid(id);
//        return new ResponseEntity<>(allprojectData, HttpStatus.OK);
        return allprojectData;
    }


}
