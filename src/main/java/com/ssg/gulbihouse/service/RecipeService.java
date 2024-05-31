package com.ssg.gulbihouse.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.gulbihouse.domain.SeasonalFood;
import com.ssg.gulbihouse.repository.SeasonalFoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Service
public class RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final SeasonalFoodRepository seasonalFoodRepository;
    private final String appId = "582b393c"; // 발급받은 Edamam API App ID
    private final String appKey = "ec7cfef1f1dd1e3428b7e9007cc68150"; // 발급받은 Edamam API Key

    public RecipeService(RestTemplate restTemplate, ObjectMapper objectMapper, SeasonalFoodRepository seasonalFoodRepository) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.seasonalFoodRepository = seasonalFoodRepository;
    }

    public String getRecipe(String foodName) {
        Optional<SeasonalFood> foodOptional = seasonalFoodRepository.findFirstByName(foodName);

        if (!foodOptional.isPresent()) {
            return "<p>음식을 찾을 수 없습니다.</p>";
        }

        SeasonalFood food = foodOptional.get();
        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://api.edamam.com/search")
                .queryParam("q", food.getEnglishName())
                .queryParam("app_id", appId)
                .queryParam("app_key", appKey)
                .toUriString();

        try {
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            logger.info("API Response: {}", jsonResponse);
            return parseRecipeJson(jsonResponse);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("HTTP error while fetching recipes: ", e);
            return "<p>레시피 정보를 가져올 수 없습니다. 나중에 다시 시도해주세요.</p>";
        } catch (Exception e) {
            logger.error("Error while fetching recipes: ", e);
            return "<p>레시피 정보를 가져올 수 없습니다. 나중에 다시 시도해주세요.</p>";
        }
    }


    private String parseRecipeJson(String jsonResponse) throws IOException {
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode hits = root.path("hits");

        if (hits.isEmpty()) {
            return "<p>레시피를 찾을 수 없습니다.</p>";
        }

        StringBuilder recipes = new StringBuilder();
        for (JsonNode hit : hits) {
            JsonNode recipe = hit.path("recipe");
            String label = recipe.path("label").asText();
            String image = recipe.path("image").asText();
            String source = recipe.path("source").asText();
            String url = recipe.path("url").asText();

            recipes.append("<div style='margin-bottom: 20px;'>")
                    .append("<h2>레시피: ").append(label).append("</h2>")
                    .append("<p><strong>출처:</strong> ").append(source).append("</p>")
                    .append("<img src='").append(image).append("' alt='").append(label).append("' style='max-width:100%; height:auto;'/>")
                    .append("<p><a href='").append(url).append("'>자세히 보기</a></p>")
                    .append("</div>");
        }

        return recipes.toString();
    }

    private String shortenUrl(String url) {
        int maxLength = 50;
        if (url.length() <= maxLength) {
            return url;
        } else {
            return url.substring(0, maxLength) + "...";
        }
    }
}
