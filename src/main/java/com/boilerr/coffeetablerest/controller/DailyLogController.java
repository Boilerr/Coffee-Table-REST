package com.boilerr.coffeetablerest.controller;

import com.boilerr.coffeetablerest.exception.ResourceNotFoundException;
import com.boilerr.coffeetablerest.model.DailyLog;
import com.boilerr.coffeetablerest.repository.DailyLogRepository;
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
public class DailyLogController {

    @Autowired
    private DailyLogRepository dailyLogRepository;

    @GetMapping("/dailylog")
    public Page<DailyLog> getDailyLogs(Pageable pageable) {
        return dailyLogRepository.findAll(pageable);
    }


    @PostMapping("/dailylog")
    public DailyLog createDailyLog(@Valid @RequestBody DailyLog dailylog) {
        System.out.println(new Timestamp(new Date().getTime()) + "    request to: /dailylog");
        return dailyLogRepository.save(dailylog);
    }

    @PutMapping("/dailylog/{dailylogId}")
    public DailyLog updateDailyLog(@PathVariable Long dailylogId,
                             @Valid @RequestBody DailyLog dailylogRequest) {
        return dailyLogRepository.findById(dailylogId)
                .map(dailylog -> {
                    dailylog.setTitle(dailylogRequest.getTitle());
                    dailylog.setDescription(dailylogRequest.getDescription());
                    return dailyLogRepository.save(dailylog);
                }).orElseThrow(() -> new ResourceNotFoundException("DailyLog not found with id " + dailylogId));
    }


    @DeleteMapping("/dailylog/{dailylogId}")
    public ResponseEntity<?> deleteDailyLog(@PathVariable Long dailylogId) {
        return dailyLogRepository.findById(dailylogId)
                .map(dailylog -> {
                    dailyLogRepository.delete(dailylog);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("DailyLog not found with id " + dailylogId));
    }
}