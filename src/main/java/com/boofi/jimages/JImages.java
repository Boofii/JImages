package com.boofi.jimages;

import com.boofi.jimages.image.ImageResourceManager;
import com.boofi.jimages.image.property.ImageProperties;
import com.boofi.jimages.resource.ResourceManager;

public class JImages {

    private static final ResourceManager IMAGE_MANAGER = new ImageResourceManager();

    public static void main(String[] args) {
        ImageProperties.register();
        IMAGE_MANAGER.findAndReadResources();
    }
}
