package com.boofi.jimages.image.property;

import com.google.gson.JsonSyntaxException;

import java.awt.*;

public class ImageProperties {

    public static void register() {
        ImagePropertyRegistry.register(new ImageProperty("scale", (jsonObject, image) -> {
            Integer scaleX = jsonObject.has("x") ? jsonObject.get("x").getAsInt() : null;
            Integer scaleY = jsonObject.has("y") ? jsonObject.get("y").getAsInt() : null;

            if(scaleX == null) {
                throw new JsonSyntaxException("Missing a required field \"x\", Expected to find an Integer.");
            }
            if(scaleY == null) {
                throw new JsonSyntaxException("Missing a required field \"y\", Expected to find an Integer.");
            }

            Graphics2D imageGraphics = image.createGraphics();
            imageGraphics.scale(scaleX, scaleY);
        }));
        ImagePropertyRegistry.register(new ImageProperty("text", ((jsonObject, image) -> {
            String text = jsonObject.has("text") ? jsonObject.get("text").getAsString() : null;
            Integer textX = jsonObject.has("x") ? jsonObject.get("x").getAsInt() : null;
            Integer textY = jsonObject.has("y") ? jsonObject.get("y").getAsInt() : null;

            if(text == null) {
                throw new JsonSyntaxException("Missing a required field \"text\", Expected to find a String.");
            }
            if(textX == null) {
                throw new JsonSyntaxException("Missing a required field \"x\", Expected to find an Integer.");
            }
            if(textY == null) {
                throw new JsonSyntaxException("Missing a required field \"y\", Expected to find an Integer.");
            }

            Graphics2D imageGraphics = image.createGraphics();
            imageGraphics.drawString(text, textX, textY);
        })));
    }
}
