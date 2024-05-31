package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Event;
import com.ssg.gulbihouse.domain.Ingredient;
import com.ssg.gulbihouse.domain.Memo;
import com.ssg.gulbihouse.domain.SeasonalFood;
import com.ssg.gulbihouse.domain.ShoppingItem;
import com.ssg.gulbihouse.dto.SearchResultItemDto;
import com.ssg.gulbihouse.repository.EventRepository;
import com.ssg.gulbihouse.repository.IngredientRepository;
import com.ssg.gulbihouse.repository.MemoRepository;
import com.ssg.gulbihouse.repository.SeasonalFoodRepository;
import com.ssg.gulbihouse.repository.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// SearchService.java
@Service
public class SearchService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private ShoppingItemRepository shoppingItemRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SeasonalFoodRepository seasonalFoodRepository;

    public Map<String, List<SearchResultItemDto>> search(String query) {
        Map<String, List<SearchResultItemDto>> results = new HashMap<>();

        List<SearchResultItemDto> ingredients = ingredientRepository.findByNameContaining(query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        results.put("ingredients", ingredients);

        List<SearchResultItemDto> memos = memoRepository.findByContentContaining(query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        results.put("memos", memos);

        List<SearchResultItemDto> shoppingItems = shoppingItemRepository.findByContentContaining(query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        results.put("shoppingItems", shoppingItems);

        List<SearchResultItemDto> events = eventRepository.findByTitleContaining(query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        results.put("events", events);

        List<SearchResultItemDto> seasonalFoods = seasonalFoodRepository.findByNameContaining(query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        results.put("seasonalFoods", seasonalFoods);

        return results;
    }

    private SearchResultItemDto convertToDto(Ingredient ingredient) {
        SearchResultItemDto dto = new SearchResultItemDto();
        dto.setId(ingredient.getId());
        dto.setType("ingredient");
        dto.setContent(ingredient.getName());
        return dto;
    }

    private SearchResultItemDto convertToDto(Memo memo) {
        SearchResultItemDto dto = new SearchResultItemDto();
        dto.setId(memo.getId());
        dto.setType("memo");
        dto.setContent(memo.getContent());
        return dto;
    }

    private SearchResultItemDto convertToDto(ShoppingItem shoppingItem) {
        SearchResultItemDto dto = new SearchResultItemDto();
        dto.setId(shoppingItem.getId());
        dto.setType("shoppingItem");
        dto.setContent(shoppingItem.getContent());
        dto.setCompleted(shoppingItem.isCompleted());
        return dto;
    }

    private SearchResultItemDto convertToDto(Event event) {
        SearchResultItemDto dto = new SearchResultItemDto();
        dto.setId(event.getId());
        dto.setType("event");
        dto.setContent(event.getTitle());
        return dto;
    }

    private SearchResultItemDto convertToDto(SeasonalFood seasonalFood) {
        SearchResultItemDto dto = new SearchResultItemDto();
        dto.setId(seasonalFood.getId());
        dto.setType("seasonalFood");
        dto.setContent(seasonalFood.getName());
        return dto;
    }
}
