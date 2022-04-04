package com.example.restapimvc.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ParseImageUrlsSerializer extends JsonSerializer<String> {
    @Value("${cloud-front.url.post-image}")
    private String CLOUD_FRONT_POST_IMAGE_URL;
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value==null || value.isEmpty()) {
            gen.writeArray(new String[0],0,0);
            return;
        }
        String[] urls = Arrays.stream(value.split(","))
                .map(s -> CLOUD_FRONT_POST_IMAGE_URL+s)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream()
                .toArray(String[]::new);
        gen.writeArray(urls,0, urls.length);
    }
}
