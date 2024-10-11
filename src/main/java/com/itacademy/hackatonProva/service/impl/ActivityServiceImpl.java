package com.itacademy.hackatonProva.service.impl;

import com.itacademy.hackatonProva.exception.ActivityIsFullException;
import com.itacademy.hackatonProva.exception.ActivityNotFoundException;
import com.itacademy.hackatonProva.model.Activity;
import com.itacademy.hackatonProva.repository.ActivityRepository;
import com.itacademy.hackatonProva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivityById(String id) {
        return activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException("Activity not found with id: " + id));
    }

    public Activity enrollUser(String id, String userId) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Activity not found with id: " + id));

        if (activity.getEnrolledUsers().size() < activity.getMaxCapacity()) {
            activity.getEnrolledUsers().add(userId);
            return activityRepository.save(activity);
        } else {
            throw new ActivityIsFullException("Max capacity reached for this activity.");
        }
    }
}