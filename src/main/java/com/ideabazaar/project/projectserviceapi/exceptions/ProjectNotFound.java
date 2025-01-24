package com.ideabazaar.project.projectserviceapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProjectNotFound extends Exception{

    public ProjectNotFound(Long id){
        super("Project is Not present in Database with id ="+id);
    }
}
