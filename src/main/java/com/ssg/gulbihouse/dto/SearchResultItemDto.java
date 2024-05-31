package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultItemDto {
    private Long id;
    private String type; // "ingredient", "memo", "shoppingItem", "event", "seasonalFood"
    private String content; // Common field for storing name or title
    private boolean completed; // Only for shopping items
}
