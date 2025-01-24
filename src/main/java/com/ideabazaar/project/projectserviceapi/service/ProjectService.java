package com.ideabazaar.project.projectserviceapi.service;

import com.ideabazaar.project.projectserviceapi.client.ResourceClient;
import com.ideabazaar.project.projectserviceapi.dto.ProjectDTO;
import com.ideabazaar.project.projectserviceapi.dto.ResourceDTO;
import com.ideabazaar.project.projectserviceapi.exceptions.ProjectNotFound;
import com.ideabazaar.project.projectserviceapi.mapper.ProjectMapper;
import com.ideabazaar.project.projectserviceapi.model.Project;
import com.ideabazaar.project.projectserviceapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private ResourceClient resourceClientimp;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.resourceClientimp = ResourceClient.create();
    }

    // C
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        // dto -> project
        Project project = ProjectMapper.INSTANCE.projectDtoToProject(projectDTO);
        // save to db
        Project updatedproject = projectRepository.save(project);
        // project -> dto
        return ProjectMapper.INSTANCE.projectToProjectDto(updatedproject);
    }

    // R
    @Transactional
    public ProjectDTO getSingleProject(Long id) {
        Project project = this.projectRepository.findById(id).orElse(null);
        // project -> dto
        ProjectDTO projectdto = ProjectMapper.INSTANCE.projectToProjectDto(project);
        List<ResourceDTO> resourcedetails = resourceClientimp.getSingleResource(id);
        if(projectdto != null){
            projectdto.setResourceDTOList(resourcedetails);
        }
        return projectdto;

//        return ProjectMapper.INSTANCE.projectToProjectDto(project);


    }
        public List<ProjectDTO> getAllProject() {
        List<Project> projects = this.projectRepository.findAll();
        // project -> dto
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project : projects) {
            projectDTOs.add(ProjectMapper.INSTANCE.projectToProjectDto(project));
        }
        return projectDTOs;
    }

    // U
    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        // dto -> user
        Project user = ProjectMapper.INSTANCE.projectDtoToProject(projectDTO);
        if(user!=null){
            Project updateduser = this.projectRepository.save(user);
            // user -> dto
            return ProjectMapper.INSTANCE.projectToProjectDto(updateduser);
        }else{
            return null;
        }
    }


    // D
    public boolean deleteProject(Long id) throws ProjectNotFound {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        } else {
            throw new ProjectNotFound(id);
        }
    }


    @Transactional
    public List<ProjectDTO> getProjectlistByUserid(Long userId) {
        List<Project> projectlist = projectRepository.findProjectsByUserId(userId);
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for(Project project : projectlist){
            ProjectDTO projectDTO = ProjectMapper.INSTANCE.projectToProjectDto(project);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }

 }
