package com.example.rooad.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "road_data")
public class RoadData {

    @Id
    private String id;

    private double latitude;
    private double longitude;

    private double vibration;
    private double speed;

    private String roadCondition; // SMOOTH / MINOR_POTHOLE / MAJOR_POTHOLE
    private int severityScore;

    private LocalDateTime timestamp;
}
