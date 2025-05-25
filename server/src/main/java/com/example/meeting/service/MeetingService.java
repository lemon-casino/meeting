package com.example.meeting.service;

import com.example.meeting.model.Meeting;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MeetingService {
    private final ConcurrentHashMap<String, Meeting> storage = new ConcurrentHashMap<>();

    public Meeting create(Meeting meeting) {
        storage.put(meeting.getId(), meeting);
        return meeting;
    }

    public Meeting get(String id) {
        return storage.get(id);
    }

    public Collection<Meeting> list() {
        return storage.values();
    }

    public void addParticipant(String meetingId, String participant) {
        Meeting meeting = storage.get(meetingId);
        if (meeting != null) {
            meeting.getParticipants().add(participant);
        }
    }
}
