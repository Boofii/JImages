package com.boofi.jimages.util;

import com.boofi.jimages.JImages;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public abstract class ResourceManager {

    private final String resourcesDirectoryName;

    protected ResourceManager(String resourcesDirectoryName) {
        this.resourcesDirectoryName = resourcesDirectoryName;
    }

    public void findAndReadResources() throws URISyntaxException, FileNotFoundException {
        Map<File, JsonElement> loader = new HashMap<>();

        String resourcesDirectoryPath = JImages.class.getClassLoader().getResource(resourcesDirectoryName).getPath();
        if(resourcesDirectoryPath != null) {
            File resourcesDirectory = new File(resourcesDirectoryPath);
            if(resourcesDirectory.isDirectory()) {
                File[] resourceFiles = resourcesDirectory.listFiles();
                if(resourceFiles != null) {
                    for(File resourceFile : resourceFiles) {
                        FileReader resourceReader = new FileReader(resourceFile);
                        JsonElement resourceJson = JsonParser.parseReader(resourceReader);

                        loader.put(resourceFile, resourceJson);
                    }
                    readResources(loader);
                }
            }
        }
    }

    protected abstract void readResources(Map<File, JsonElement> loader);
}
