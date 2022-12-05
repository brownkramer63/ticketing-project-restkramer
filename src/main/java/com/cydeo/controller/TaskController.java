package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getTasks(){
     List<TaskDTO> taskDTOList= taskService.listAllTasks() ;
     return ResponseEntity.ok(new ResponseWrapper("list of tasks",taskDTOList, HttpStatus.OK));
    }
@GetMapping("/{id}")
@RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getTaskById(@PathVariable("id") Long id){
      TaskDTO taskDTO= taskService.findById(id);
      return ResponseEntity.ok(new ResponseWrapper("task by id",taskDTO,HttpStatus.OK));

    }
@PostMapping
@RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO){

        taskService.save(taskDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("created task",
            taskDTO,HttpStatus.OK ));

}

@DeleteMapping("/{taskid}")
@RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable ("taskid") Long taskid){
        taskService.delete(taskid);
    return ResponseEntity.ok(new ResponseWrapper("task deleted",HttpStatus.OK));
    }

   @PutMapping
   @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO){

        taskService.update(taskDTO);
       return ResponseEntity.ok(new ResponseWrapper("updated task",HttpStatus.OK));
   }

   @GetMapping("/employee/pending-tasks")
   @RolesAllowed("Employee")
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){
        List<TaskDTO> taskDTOList= taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("tasks not completed",HttpStatus.OK));


    }

    @PutMapping("/employee/update/")
    @RolesAllowed("Employee")
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(@RequestBody TaskDTO task){
        taskService.update(task);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated",HttpStatus.OK));

    }


    @GetMapping("/employee/archive")
    @RolesAllowed("Employee")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks(){
        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatus(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved",taskDTOList,HttpStatus.OK));

    }

}
