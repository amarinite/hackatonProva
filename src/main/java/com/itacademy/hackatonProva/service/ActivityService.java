package com.itacademy.hackatonProva.service;

import com.itacademy.hackatonProva.exception.ActivityIsFullException;
import com.itacademy.hackatonProva.exception.ActivityNotFoundException;
import com.itacademy.hackatonProva.model.Activity;

import java.util.List;

public interface ActivityService {
    Activity createActivity(Activity activity);

    List<Activity> getAllActivities();

    Activity getActivityById(String id);

    Activity enrollUser(String id, String userId);
}
