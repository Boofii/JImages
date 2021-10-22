package com.boofi.jimages.image.property;

import com.google.gson.JsonObject;

import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

public class ImageProperty {

    private final String id;
    private final BiConsumer<JsonObject, BufferedImage> action;

    public ImageProperty(String id, BiConsumer<JsonObject, BufferedImage> action) {
        this.id = id;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public BiConsumer<JsonObject, BufferedImage> getAction() {
        return action;
    }
}
