package com.example.meeting.controller;

import com.example.meeting.model.Meeting;
import com.example.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService service;

    public MeetingController(MeetingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Meeting> create(@RequestBody Meeting meeting) {
        return ResponseEntity.ok(service.create(meeting));
    }

    @GetMapping
    public Collection<Meeting> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> get(@PathVariable String id) {
        Meeting meeting = service.get(id);
        if (meeting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(meeting);
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<Void> join(@PathVariable String id, @RequestParam String name) {
        service.addParticipant(id, name);
        return ResponseEntity.ok().build();
    }
}

