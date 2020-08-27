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
import java.util.Optional;


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

    @GetMapping("/tasks/{taskId}")
    public Optional<Task> getTask(@PathVariable Long taskId) {
        return taskRepository.findById(taskId);
    }
    
    @GetMapping("/tasks")
    public List<Task> getTasksByTag(@RequestParam String tag) {
        return taskRepository.retrieveByTag(tag);
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
                    task.setTitle(taskRequest.getTitle());
                    task.setDescription(taskRequest.getDescription());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTaskById(@PathVariable Long taskId, @Valid @RequestBody Task taskRequest) {
        if(!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task not found with id " + taskId);
        }
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setTitle(taskRequest.getTitle());
                    task.setDescription(taskRequest.getDescription());
                    task.setTags(taskRequest.getTags());
                    task.setFlagged(taskRequest.getFlagged());
                    task.setActive(taskRequest.getActive());
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

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

    }

}
