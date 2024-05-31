package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.dto.SearchResultItemDto;
import com.ssg.gulbihouse.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/api/search")
    public Map<String, List<SearchResultItemDto>> search(@RequestParam("keyword") String keyword) {
        return searchService.search(keyword);
    }
}
