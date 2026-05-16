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
    private final AiService aiService;

    // CREATE
    public RoadData processAndSave(RoadData data) {

        double vibration = data.getVibration();

        String prediction = aiService.getPrediction(
                data.getVibration(),
                data.getSpeed()
        );

        data.setRoadCondition(prediction);

        if(prediction.equals("SMOOTH")) {
            data.setSeverityScore(1);
        }
        else if(prediction.equals("MINOR_POTHOLE")) {
            data.setSeverityScore(5);
        }
        else {
            data.setSeverityScore(9);
        }

        data.setTimestamp(LocalDateTime.now());

        return repository.save(data);
    }

    // READ ALL
    public List<RoadData> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public RoadData getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // FILTER BY CONDITION
    public List<RoadData> getByCondition(String condition) {
        return repository.findByRoadCondition(condition);
    }

    // DATE RANGE
    public List<RoadData> getByDateRange(LocalDateTime start, LocalDateTime end) {
        return repository.findByTimestampBetween(start, end);
    }

    // UPDATE
    public RoadData updateRoadData(String id, RoadData updatedData) {

        RoadData existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        existing.setLatitude(updatedData.getLatitude());
        existing.setLongitude(updatedData.getLongitude());
        existing.setVibration(updatedData.getVibration());
        existing.setSpeed(updatedData.getSpeed());

        // Recalculate road condition
        if (updatedData.getVibration() >= 7) {
            existing.setRoadCondition("MAJOR_POTHOLE");
            existing.setSeverityScore(9);

        } else if (updatedData.getVibration() >= 4) {
            existing.setRoadCondition("MINOR_POTHOLE");
            existing.setSeverityScore(5);

        } else {
            existing.setRoadCondition("SMOOTH");
            existing.setSeverityScore(1);
        }

        return repository.save(existing);
    }

    // DELETE
    public void deleteRoadData(String id) {

        RoadData existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        repository.delete(existing);
    }
}
