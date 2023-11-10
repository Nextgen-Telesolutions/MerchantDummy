package com.ng.sb.dummy;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonDataService {
	
    @Value("${dummy.json.path}")
    private String filePath;

    public JsonNode loadJsonData() throws IOException {
        Resource resource = new FileSystemResource(filePath + "sample.json");
        ObjectMapper objectMapper = new ObjectMapper();
        if (resource.exists()) {
            try (InputStream inputStream = resource.getInputStream()){
                return objectMapper.readTree(inputStream);
            }
        } else {
            return null;
        }
    }
}
