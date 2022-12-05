package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

@GetMapping
@RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjects(){
        List<ProjectDTO> projectDTOList= projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("list of projects",projectDTOList, HttpStatus.OK));

    }

@GetMapping("/{code}")
@RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code){
ProjectDTO projectDTO= projectService.getByProjectCode(code );
return ResponseEntity.ok(new ResponseWrapper("project by code",projectDTO,HttpStatus.OK ));
    }

@PostMapping
@RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO projectDTO){
     projectService.save(projectDTO);
     return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("created project",
             projectDTO,HttpStatus.OK ));

}

@PutMapping
@RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO projectDTO){
    projectService.update(projectDTO);
    return ResponseEntity.ok(new ResponseWrapper("updated project",HttpStatus.OK));
}

    @DeleteMapping("/{projectcode}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectcode") String projectcode){
        projectService.delete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("project deleted",HttpStatus.OK));

    }

    @GetMapping("/manager/project-status")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjectByManager(){
List<ProjectDTO> projectDTOList=projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("list of projects",projectDTOList, HttpStatus.OK));
//this method is hazy has same implementation as above but is hardcoded
    }

    @PutMapping("/manager/complete/{projectCode}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode")String projectCode){

        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("project is completed",HttpStatus.OK));

    }
}
