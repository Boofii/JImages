package com.boofi.jimages.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonResourceManager extends ResourceManager {

    public JsonResourceManager(String resourcesName) {
        super(resourcesName);
    }

    @Override
    protected void readResources(Map<String, InputStream> loader) {
        Map<String, JsonElement> jsonLoader = new HashMap<>();
        loader.forEach((string, inputStream) -> {
            if(string.endsWith(".json")) {
                Reader resourceReader = new InputStreamReader(inputStream);
                JsonElement resourceJson = JsonParser.parseReader(resourceReader);
                if(resourceJson != null) {
                    jsonLoader.put(string, resourceJson);
                }
            }
        });
        this.readJsonResources(jsonLoader);
    }

    protected abstract void readJsonResources(Map<String, JsonElement> loader);
}
