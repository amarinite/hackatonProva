package com.itacademy.hackatonProva;

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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.itacademy.hackatonProva.controller.UserController;
import com.itacademy.hackatonProva.model.User;
import com.itacademy.hackatonProva.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId("1");
        user.setFirstName("Mar");
        user.setLastName("Lucas");
        user.setEmail("marlucas@loscuarenta.com");
        user.setAge(28);

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Mar\",\"lastName\":\"Lucas\",\"email\":\"marlucas@loscuarenta.com\",\"age\":28}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mar"))
                .andExpect(jsonPath("$.lastName").value("Lucas"))
                .andExpect(jsonPath("$.email").value("marlucas@loscuarenta.com"))
                .andExpect(jsonPath("$.age").value(28));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId("1");
        user1.setFirstName("Mar");
        user1.setLastName("Lucas");
        user1.setEmail("marlucas@loscuarenta.com");
        user1.setAge(28);

        User user2 = new User();
        user2.setId("2");
        user2.setFirstName("Rauw");
        user2.setLastName("Alejandro");
        user2.setEmail("rauwalejandro@universal.com");
        user2.setAge(30);

        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Mar"))
                .andExpect(jsonPath("$[1].name").value("Rauw"));
    }
}