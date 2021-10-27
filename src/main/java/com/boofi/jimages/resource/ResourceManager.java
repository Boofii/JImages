package com.boofi.jimages.resource;

import com.boofi.jimages.JImages;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class ResourceManager {

    private final String resourcesName;

    public ResourceManager(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public void findAndReadResources() {
        InputStream resourcesStream = JImages.class.getClassLoader().getResourceAsStream(resourcesName);
        if(resourcesStream != null) {
            Scanner resourcesStreamReader = new Scanner(resourcesStream);
            Map<String, InputStream> loader = new HashMap<>();
            while(resourcesStreamReader.hasNextLine()) {
                String currentResourceName = resourcesStreamReader.nextLine();
                InputStream currentResourceStream = JImages.class.getClassLoader().getResourceAsStream(resourcesName + "\\" + currentResourceName);

                loader.put(currentResourceName, currentResourceStream);
            }
            this.readResources(loader);
        }
    }

    protected abstract void readResources(Map<String, InputStream> loader);
}
