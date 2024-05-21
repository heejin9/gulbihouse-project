package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Ingredient;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddIngredientRequest;
import com.ssg.gulbihouse.dto.UpdateIngredientRequest;
import com.ssg.gulbihouse.repository.IngredientRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceTest {

    @InjectMocks
    private IngredientService ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private UserRepository userRepository;

    private User user;
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .nickname("testUser")
                .email("test@example.com")
                .password("password")
                .role(User.Role.USER)
                .build();

        ingredient = Ingredient.builder()
                .name("Tomato")
                .quantity(5)
                .expirationDate(LocalDate.now().plusDays(10))
                .status(Ingredient.Status.REFRIGERATED)
                .user(user)
                .build();
        setIngredientId(ingredient, 1L);  // id를 직접 설정합니다.
    }

    private void setIngredientId(Ingredient ingredient, Long id) {
        try {
            Field field = Ingredient.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(ingredient, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSaveIngredient() {
        AddIngredientRequest request = new AddIngredientRequest();
        request.setName("Tomato");
        request.setQuantity(5);
        request.setExpirationDate(LocalDate.now().plusDays(10));
        request.setStatus(Ingredient.Status.REFRIGERATED);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        Long ingredientId = ingredientService.save(request, user.getId());

        assertNotNull(ingredientId);
        verify(ingredientRepository, times(1)).save(any(Ingredient.class));
    }

    @Test
    void testFindIngredientsByUserId() {
        when(ingredientRepository.findByUserId(user.getId())).thenReturn(List.of(ingredient));

        List<Ingredient> ingredients = ingredientService.findByUserId(user.getId());

        assertNotNull(ingredients);
        assertEquals(1, ingredients.size());
        assertEquals("Tomato", ingredients.get(0).getName());
    }

    @Test
    void testDeleteIngredient() {
        Long ingredientId = 1L;

        doNothing().when(ingredientRepository).deleteById(ingredientId);

        ingredientService.delete(ingredientId);

        verify(ingredientRepository, times(1)).deleteById(ingredientId);
    }

    @Test
    void testUpdateIngredient() {
        Long ingredientId = 1L;
        UpdateIngredientRequest request = new UpdateIngredientRequest();
        request.setName("Updated Tomato");
        request.setQuantity(10);
        request.setExpirationDate(LocalDate.now().plusDays(20));
        request.setStatus(Ingredient.Status.FROZEN);

        when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.of(ingredient));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        ingredientService.update(ingredientId, request);

        verify(ingredientRepository, times(1)).save(ingredient);
        assertEquals("Updated Tomato", ingredient.getName());
        assertEquals(10, ingredient.getQuantity());
        assertEquals(Ingredient.Status.FROZEN, ingredient.getStatus());
    }
}
