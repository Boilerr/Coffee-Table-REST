package com.boilerr.coffeetablerest.controller;

import com.boilerr.coffeetablerest.exception.ResourceNotFoundException;
import com.boilerr.coffeetablerest.model.Inbox;
import com.boilerr.coffeetablerest.repository.InboxRepository;
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
public class InboxController {

    @Autowired
    private InboxRepository inboxRepository;

    @GetMapping("/inbox")
    public Page<Inbox> getInboxes(Pageable pageable) {
        return inboxRepository.findAll(pageable);
    }


    @PostMapping("/inbox")
    public Inbox createInbox(@Valid @RequestBody Inbox inbox) {
        System.out.println(new Timestamp(new Date().getTime()) + "    request to: /inbox");
        return inboxRepository.save(inbox);
    }

    @PutMapping("/inbox/{inboxId}")
    public Inbox updateInbox(@PathVariable Long inboxId,
                             @Valid @RequestBody Inbox inboxRequest) {
        return inboxRepository.findById(inboxId)
                .map(inbox -> {
                    inbox.setTitle(inboxRequest.getTitle());
                    inbox.setDescription(inboxRequest.getDescription());
                    return inboxRepository.save(inbox);
                }).orElseThrow(() -> new ResourceNotFoundException("Inbox not found with id " + inboxId));
    }


    @DeleteMapping("/inbox/{inboxId}")
    public ResponseEntity<?> deleteInbox(@PathVariable Long inboxId) {
        return inboxRepository.findById(inboxId)
                .map(inbox -> {
                    inboxRepository.delete(inbox);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Inbox not found with id " + inboxId));
    }
}