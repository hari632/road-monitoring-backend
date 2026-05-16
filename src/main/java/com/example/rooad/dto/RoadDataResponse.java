package com.example.rooad.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RoadDataResponse {

    private String id;
    private double latitude;
    private double longitude;
    private double vibration;
    private double speed;
    private String locationName;
    private String roadCondition;
    private int severityScore;
    private LocalDateTime timestamp;
}