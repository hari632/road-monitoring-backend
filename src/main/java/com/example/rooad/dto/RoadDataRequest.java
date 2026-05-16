package com.example.rooad.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class RoadDataRequest {

    @NotNull(message = "Latitude required")
    private Double latitude;

    @NotNull(message = "Longitude required")
    private Double longitude;

    @Min(value = 0, message = "Vibration must be >= 0")
    @Max(value = 50, message = "Vibration must be <= 50")
    private Double vibration;

    @Min(value = 0, message = "Speed must be >= 0")
    @Max(value = 150, message = "Speed must be <= 150")
    private Double speed;
    private String locationName;
}