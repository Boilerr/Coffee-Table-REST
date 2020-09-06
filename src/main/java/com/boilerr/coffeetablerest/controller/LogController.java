package com.boilerr.coffeetablerest.controller;

import com.boilerr.coffeetablerest.exception.ResourceNotFoundException;
import com.boilerr.coffeetablerest.model.Log;
import com.boilerr.coffeetablerest.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/log")
    public Page<Log> getLogs(Pageable pageable) {
        return logRepository.findAll(pageable);
    }


    @PostMapping("/log")
    public Log createLog(@Valid @RequestBody Log log) {
        System.out.println(new Timestamp(new Date().getTime()) + "    request to: /log");
        return logRepository.save(log);
    }

    @PutMapping("/log/{logId}")
    public Log updateLog(@PathVariable Long logId,
                             @Valid @RequestBody Log logRequest) {
        return logRepository.findById(logId)
                .map(log -> {
                    log.setSource(logRequest.getSource());
                    log.setCategory(logRequest.getCategory());
                    log.setDescription(logRequest.getDescription());
                    return logRepository.save(log);
                }).orElseThrow(() -> new ResourceNotFoundException("Log not found with id " + logId));
    }


    @DeleteMapping("/log/{logId}")
    public ResponseEntity<?> deleteLog(@PathVariable Long logId) {
        return logRepository.findById(logId)
                .map(log -> {
                    logRepository.delete(log);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Log not found with id " + logId));
    }
}