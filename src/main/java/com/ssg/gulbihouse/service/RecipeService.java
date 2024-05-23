package com.ssg.gulbihouse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeService {

    @Value("${recipe.api.url}")
    private String apiUrl;

    @Value("${recipe.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public RecipeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRecipe(String foodName) {
        String url = String.format("%s?query=%s&apiKey=%s", apiUrl, foodName, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
