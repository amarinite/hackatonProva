package com.itacademy.hackatonProva;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.hackatonProva.controller.ActivityController;
import com.itacademy.hackatonProva.model.Activity;
import com.itacademy.hackatonProva.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private ActivityService activityService;

	@InjectMocks
	private ActivityController activityController;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testCreateActivity() throws Exception {
		Activity activity = new Activity();
		activity.setId("1");
		activity.setName("Cooking Class");
		activity.setDescription("Let's learn Mediterranean cuisine.");
		activity.setMaxCapacity(20);
		activity.setEnrolledUsers(new ArrayList<>());

		when(activityService.createActivity(any(Activity.class))).thenReturn(activity);

		mockMvc.perform(post("/api/activities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(activity)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.name").value("Cooking Class"));
	}

	@Test
	public void testGetAllActivities() throws Exception {
		List<Activity> activities = new ArrayList<>();

		Activity activity1 = new Activity();
		activity1.setId("1");
		activity1.setName("Cooking Class");
		activity1.setDescription("Let's learn Mediterranean cuisine.");
		activity1.setMaxCapacity(20);
		activity1.setEnrolledUsers(new ArrayList<>());

		Activity activity2 = new Activity();
		activity2.setId("2");
		activity2.setName("Photography Class");
		activity2.setDescription("Lessons to test your photography skills.");
		activity2.setMaxCapacity(15);
		activity2.setEnrolledUsers(new ArrayList<>());

		activities.add(activity1);
		activities.add(activity2);

		when(activityService.getAllActivities()).thenReturn(activities);

		mockMvc.perform(get("/api/activities"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].name").value("Cooking Class"))
				.andExpect(jsonPath("$[1].name").value("Photography Class"));
	}
}