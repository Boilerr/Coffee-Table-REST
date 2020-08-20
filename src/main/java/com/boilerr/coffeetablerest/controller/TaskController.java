package com.boilerr.coffeetablerest.controller;

import com.boilerr.coffeetablerest.exception.ResourceNotFoundException;
import com.boilerr.coffeetablerest.model.Task;
import com.boilerr.coffeetablerest.repository.ProjectRepository;
import com.boilerr.coffeetablerest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects/{projectId}/tasks")
    public List<Task> getTasksByProjectId(@PathVariable Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @PostMapping("/projects/{projectId}/tasks")
    public Task addTask(@PathVariable Long projectId,
                            @Valid @RequestBody Task task) {
        return projectRepository.findById(projectId)
                .map(project -> {
                    task.setProject(project);
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));
    }

    @PutMapping("/projects/{projectId}/tasks/{taskId}")
    public Task updateTask(@PathVariable Long projectId,
                               @PathVariable Long taskId,
                               @Valid @RequestBody Task taskRequest) {
        if(!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found with id " + projectId);
        }

        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setText(taskRequest.getText());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long projectId,
                                          @PathVariable Long taskId) {
        if(!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found with id " + projectId);
        }

        return taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

    }
}
