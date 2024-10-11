package com.itacademy.hackatonProva.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.hackatonProva.model.Activity;
import com.itacademy.hackatonProva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {


    private final ActivityService activityService;
    private final ObjectMapper objectMapper;
    @Autowired
    public ActivityController(ActivityService activityService, ObjectMapper objectMapper) {
        this.activityService = activityService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return activities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable String id) {
        Activity activity = activityService.getActivityById(id);
        return ResponseEntity.ok(activity);

    }

    @PostMapping("/{activityId}/enroll/{userId}")
    public ResponseEntity<Activity> enrollUser(@PathVariable String activityId, @PathVariable String userId) {
        Activity updatedActivity = activityService.enrollUser(activityId, userId);
        return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importActivities(@RequestParam("file") MultipartFile file) throws IOException {
        List<Activity> activities = objectMapper.readValue(file.getInputStream(), new TypeReference<>() {
        });
        for (Activity activity : activities) {
            activityService.createActivity(activity);
        }
        return new ResponseEntity<>("Activities imported successfully", HttpStatus.OK);
    }

    @GetMapping("/export")
    public ResponseEntity<List<Activity>> exportActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
}