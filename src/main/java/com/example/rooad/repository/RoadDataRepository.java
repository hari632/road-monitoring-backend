package com.example.rooad.repository;

import com.example.rooad.model.RoadData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RoadDataRepository extends MongoRepository<RoadData, String> {

    // 🔍 Get all records by road condition
    List<RoadData> findByRoadCondition(String roadCondition);

    // 📅 Get records between two timestamps
    List<RoadData> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
