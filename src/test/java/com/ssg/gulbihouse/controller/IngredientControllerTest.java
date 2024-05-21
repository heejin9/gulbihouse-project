package com.ssg.gulbihouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.gulbihouse.config.TestSecurityConfig;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.domain.Ingredient;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddIngredientRequest;
import com.ssg.gulbihouse.dto.UpdateIngredientRequest;
import com.ssg.gulbihouse.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IngredientController.class)
@Import(TestSecurityConfig.class)  // TestSecurityConfig 설정을 가져옵니다.
class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientService ingredientService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private CustomUserDetails customUserDetails;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

        user = User.builder()
                .id(1L)
                .nickname("testUser")
                .email("test@example.com")
                .password("password")
                .role(User.Role.USER)
                .build();

        customUserDetails = new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities())
        );
    }

    @Test
    @WithMockUser
    void testAddIngredient() throws Exception {
        AddIngredientRequest request = new AddIngredientRequest();
        request.setName("Tomato");
        request.setQuantity(5);
        request.setExpirationDate(LocalDate.now().plusDays(10));
        request.setStatus(Ingredient.Status.REFRIGERATED);

        when(ingredientService.save(any(AddIngredientRequest.class), any(Long.class))).thenReturn(1L);

        mockMvc.perform(post("/api/ingredients")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testGetIngredients() throws Exception {
        Ingredient ingredient = Ingredient.builder()
                .name("Tomato")
                .quantity(5)
                .expirationDate(LocalDate.now().plusDays(10))
                .status(Ingredient.Status.REFRIGERATED)
                .user(user)
                .build();

        when(ingredientService.findByUserId(user.getId())).thenReturn(List.of(ingredient));

        mockMvc.perform(get("/api/ingredients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tomato"));
    }

    @Test
    @WithMockUser
    void testDeleteIngredient() throws Exception {
        Long ingredientId = 1L;

        mockMvc.perform(delete("/api/ingredients/{id}", ingredientId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testUpdateIngredient() throws Exception {
        Long ingredientId = 1L;
        UpdateIngredientRequest request = new UpdateIngredientRequest();
        request.setName("Updated Tomato");
        request.setQuantity(10);
        request.setExpirationDate(LocalDate.now().plusDays(20));
        request.setStatus(Ingredient.Status.FROZEN);

        mockMvc.perform(put("/api/ingredients/{id}", ingredientId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
