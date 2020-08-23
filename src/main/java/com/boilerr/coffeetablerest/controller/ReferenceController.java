package com.boilerr.coffeetablerest.controller;

import com.boilerr.coffeetablerest.exception.ResourceNotFoundException;
import com.boilerr.coffeetablerest.model.Reference;
import com.boilerr.coffeetablerest.repository.ReferenceRepository;
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
public class ReferenceController {

    @Autowired
    private ReferenceRepository referenceRepository;

    @GetMapping("/reference")
    public Page<Reference> getReferences(Pageable pageable) {
        return referenceRepository.findAll(pageable);
    }


    @PostMapping("/reference")
    public Reference createReference(@Valid @RequestBody Reference reference) {
        System.out.println(new Timestamp(new Date().getTime()) + "    request to: /reference");
        return referenceRepository.save(reference);
    }

    @PutMapping("/reference/{referenceId}")
    public Reference updateReference(@PathVariable Long referenceId,
                             @Valid @RequestBody Reference referenceRequest) {
        return referenceRepository.findById(referenceId)
                .map(reference -> {
                    reference.setTitle(referenceRequest.getTitle());
                    reference.setDescription(referenceRequest.getDescription());
                    return referenceRepository.save(reference);
                }).orElseThrow(() -> new ResourceNotFoundException("Reference not found with id " + referenceId));
    }


    @DeleteMapping("/reference/{referenceId}")
    public ResponseEntity<?> deleteReference(@PathVariable Long referenceId) {
        return referenceRepository.findById(referenceId)
                .map(reference -> {
                    referenceRepository.delete(reference);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Reference not found with id " + referenceId));
    }
}