package com.hotelbooker.helpers;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHelpers {

    public static <T> Map<String, Object> formatListResponse(final Page<T> page) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", page.getContent());
        response.put("page", page.getNumber() + 1);
        response.put("pages", page.getTotalPages());
        response.put("total", page.getTotalElements());
        response.put("take", page.getSize());
        return response;
    }
}
