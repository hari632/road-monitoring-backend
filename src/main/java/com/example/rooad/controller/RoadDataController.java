package com.example.rooad.controller;

import com.example.rooad.dto.RoadDataRequest;
import com.example.rooad.dto.RoadDataResponse;
import com.example.rooad.model.RoadData;
import com.example.rooad.service.RoadDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/road-data")
@RequiredArgsConstructor
@CrossOrigin
public class RoadDataController {

    private final RoadDataService service;





    @GetMapping
    public List<RoadData> getAll() {
        return service.getAll();
    }

    // 📥 GET by ID
    @GetMapping("/{id}")
    public RoadData getById(@PathVariable String id) {
        return service.getById(id);
    }

    // 📥 Filter by condition
    @GetMapping("/filter")
    public List<RoadData> filterByCondition(@RequestParam String condition) {
        return service.getByCondition(condition);
    }

    // 📥 Filter by date range
    @GetMapping("/date-range")
    public List<RoadData> filterByDate(
            @RequestParam String start,
            @RequestParam String end) {

        return service.getByDateRange(
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );

    }
    @PostMapping
    public RoadDataResponse save(@Valid @RequestBody RoadDataRequest request) {

        RoadData data = RoadData.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .vibration(request.getVibration())
                .speed(request.getSpeed())
                .build();

        RoadData saved = service.processAndSave(data);

        return RoadDataResponse.builder()
                .id(saved.getId())
                .latitude(saved.getLatitude())
                .longitude(saved.getLongitude())
                .vibration(saved.getVibration())
                .speed(saved.getSpeed())
                .roadCondition(saved.getRoadCondition())
                .severityScore(saved.getSeverityScore())
                .timestamp(saved.getTimestamp())
                .build();
    }
}
