package com.boofi.jimages.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class ImageResourceManager extends ResourceManager {

    private final String resourcesName;

    public ImageResourceManager(String resourcesName) {
        super(resourcesName);
        this.resourcesName = resourcesName;
    }

    @Override
    protected void readResources(Map<String, InputStream> loader) {
        Map<String, BufferedImage> imageLoader = new HashMap<>();
        loader.forEach((string, inputStream) -> {
            if(string.endsWith(".png")) {
                try {
                    BufferedImage resourceImage = ImageIO.read(inputStream);
                    imageLoader.put(string, resourceImage);
                } catch(IOException e) {
                    System.out.println("Error loading image resources in resource manager " +  resourcesName + ": " + e.getMessage());
                }
            }
        });
        this.readImageResources(imageLoader);
    }

    protected abstract void readImageResources(Map<String, BufferedImage> loader);
}
