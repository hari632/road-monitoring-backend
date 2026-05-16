package com.example.rooad.service;

import com.example.rooad.dto.AiPredictionResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getPrediction(double vibration, double speed) {

        String url = "http://127.0.0.1:5000/predict";

        Map<String, Object> request = new HashMap<>();
        request.put("vibration", vibration);
        request.put("speed", speed);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<AiPredictionResponse> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        AiPredictionResponse.class
                );

        return response.getBody().getPrediction();
    }
}
