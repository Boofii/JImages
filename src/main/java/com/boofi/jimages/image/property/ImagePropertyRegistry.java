package com.boofi.jimages.image.property;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ImagePropertyRegistry {

    private static final List<ImageProperty> properties = new LinkedList<>();

    public static void register(ImageProperty property) {
        if(!properties.contains(property)) {
            properties.add(property);
        }
    }

    public static Stream<ImageProperty> getProperties() {
        return properties.stream();
    }

    public static ImageProperty getById(String id) {
        Optional<ImageProperty> optionalImageProperty = getProperties().filter((property) -> property.getId().equals(id)).findFirst();
        return optionalImageProperty.orElse(null);
    }
}
