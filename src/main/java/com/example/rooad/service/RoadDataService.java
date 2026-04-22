package com.example.rooad.service;

import com.example.rooad.model.RoadData;
import com.example.rooad.repository.RoadDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadDataService {

    private final RoadDataRepository repository;


    public RoadData processAndSave(RoadData data) {

        double vibration = data.getVibration();

        if (vibration < 3) {
            data.setRoadCondition("SMOOTH");
            data.setSeverityScore(1);
        } else if (vibration < 7) {
            data.setRoadCondition("MINOR_POTHOLE");
            data.setSeverityScore(5);
        } else {
            data.setRoadCondition("MAJOR_POTHOLE");
            data.setSeverityScore(9);
        }

        data.setTimestamp(LocalDateTime.now());

        return repository.save(data);
    }


    public List<RoadData> getAll() {
        return repository.findAll();
    }


    public RoadData getById(String id) {
        return repository.findById(id).orElse(null);
    }


    public List<RoadData> getByCondition(String condition) {
        return repository.findByRoadCondition(condition);
    }


    public List<RoadData> getByDateRange(LocalDateTime start, LocalDateTime end) {
        return repository.findByTimestampBetween(start, end);
    }
}
