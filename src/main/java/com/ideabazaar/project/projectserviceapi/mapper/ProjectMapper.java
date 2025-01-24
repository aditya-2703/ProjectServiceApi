package com.ideabazaar.project.projectserviceapi.mapper;

import com.ideabazaar.project.projectserviceapi.dto.ProjectDTO;
import com.ideabazaar.project.projectserviceapi.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    // Map fields from User -> UserDto
    ProjectDTO projectToProjectDto(Project user);

    // Map fields from UserDto -> User
    Project projectDtoToProject(ProjectDTO userDto);
}
