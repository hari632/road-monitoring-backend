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

    // CREATE
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

    // READ ALL
    @GetMapping
    public List<RoadData> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public RoadData getById(@PathVariable String id) {
        return service.getById(id);
    }

    // FILTER BY CONDITION
    @GetMapping("/condition/{condition}")
    public List<RoadData> getByCondition(@PathVariable String condition) {
        return service.getByCondition(condition);
    }

    // DATE RANGE
    @GetMapping("/date-range")
    public List<RoadData> getByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return service.getByDateRange(start, end);
    }

    // UPDATE
    @PutMapping("/{id}")
    public RoadData updateRoadData(
            @PathVariable String id,
            @RequestBody RoadData updatedData) {

        return service.updateRoadData(id, updatedData);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteRoadData(@PathVariable String id) {

        service.deleteRoadData(id);

        return "Road data deleted successfully";
    }
}
