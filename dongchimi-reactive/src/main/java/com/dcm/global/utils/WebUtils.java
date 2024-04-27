package com.dcm.global.utils;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class WebUtils {

    public static String getQueryParam(String uri, String paramName) {
        MultiValueMap<String, String> params = UriComponentsBuilder.fromUriString(uri).build().getQueryParams();
        String value = params.getFirst(paramName);
        if (value == null) {
            throw new IllegalArgumentException("Required parameter '" + paramName + "' is missing");
        }
        return value;
    }

}
