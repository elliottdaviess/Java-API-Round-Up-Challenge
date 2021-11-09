package com.starling.roundup.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class HeadersConfiguration {

    @Value("${accessToken}")
    private String token;

    public HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);
        return headers;
    }
}