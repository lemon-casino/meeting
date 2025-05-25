package com.example.meeting.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Meeting {
    private String id = UUID.randomUUID().toString();
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String host;
    private List<String> participants = new ArrayList<>();
}
