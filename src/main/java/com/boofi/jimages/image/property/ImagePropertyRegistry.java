package com.boofi.jimages.image.property;

import java.util.Collection;
import java.util.HashMap;

public class ImagePropertyRegistry {

    private static final HashMap<String, ImageProperty> registeredProperties = new HashMap<>();

    public static Collection<ImageProperty> getRegisteredProperties() {
        return registeredProperties.values();
    }

    public static ImageProperty get(String id) {
        return registeredProperties.get(id);
    }

    public static void register(ImageProperty property) {
        if(!registeredProperties.containsKey(property.getId())) {
            registeredProperties.put(property.getId(), property);
        }
    }
}
