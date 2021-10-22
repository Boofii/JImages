package com.boofi.jimages;

import com.boofi.jimages.image.ImageManager;
import com.boofi.jimages.image.property.ImageProperties;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class JImages {

    private static final ImageManager IMAGE_MANAGER = new ImageManager();

    public static void main(String[] args) {
        ImageProperties.register();
        try {
            IMAGE_MANAGER.findAndReadResources();
        } catch (URISyntaxException | FileNotFoundException e) {
            System.out.println("Error applying image resources: "+e.getMessage()+"");
        }
    }
}
