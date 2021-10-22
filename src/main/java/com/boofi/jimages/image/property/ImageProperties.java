package com.boofi.jimages.image.property;

import com.boofi.jimages.data.ColorTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.awt.*;

public class ImageProperties {

    private static final Gson GSON = (new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(Color.class, new ColorTypeAdapter())).create();

    public static void register() {
        ImagePropertyRegistry.register(new ImageProperty("line", (json, image) -> {
            Color color = json.has("color") ? GSON.fromJson(json.get("color"), Color.class) : null;
            Integer x1Point = json.has("x1") ? json.get("x1").getAsInt() : null;
            Integer y1Point = json.has("y1") ? json.get("y1").getAsInt() : null;
            Integer x2Point = json.has("x2") ? json.get("x2").getAsInt() : null;
            Integer y2Point = json.has("y2") ? json.get("y2").getAsInt() : null;

            if(x1Point == null) {
                throw new JsonSyntaxException("Missing required field: \"x1\"");
            }
            if(y1Point == null) {
                throw new JsonSyntaxException("Missing required field: \"y1\"");
            }
            if(x2Point == null) {
                throw new JsonSyntaxException("Missing required field: \"x2\"");
            }
            if(y2Point == null) {
                throw new JsonSyntaxException("Missing required field: \"y2\"");
            }

            Graphics2D imageGraphics = image.createGraphics();
            if(color != null) {
                imageGraphics.setColor(color);
            }
            imageGraphics.drawLine(x1Point, y1Point, x2Point, y2Point);
        }));
        ImagePropertyRegistry.register(new ImageProperty("text", (json, image) -> {
            Color color = json.has("color") ? GSON.fromJson(json.get("color"), Color.class) : null;
            String text = json.has("text") ? json.get("text").getAsString() : null;
            Integer xPoint = json.has("x") ? json.get("x").getAsInt() : null;
            Integer yPoint = json.has("y") ? json.get("y").getAsInt() : null;

            if(xPoint == null) {
                throw new JsonSyntaxException("Missing required field: \"x\"");
            }
            if(yPoint == null) {
                throw new JsonSyntaxException("Missing required field: \"y\"");
            }

            Graphics2D imageGraphics = image.createGraphics();
            if(color != null) {
                imageGraphics.setColor(color);
            }
            imageGraphics.drawString(text, xPoint, yPoint);
        }));
        ImagePropertyRegistry.register(new ImageProperty("rectangle", (json, image) -> {
            Color color = json.has("color") ? GSON.fromJson(json.get("color"), Color.class) : null;
            Integer xPoint = json.has("x") ? json.get("x").getAsInt() : null;
            Integer yPoint = json.has("y") ? json.get("y").getAsInt() : null;
            Integer width = json.has("width") ? json.get("width").getAsInt() : null;
            Integer height = json.has("height") ? json.get("height").getAsInt() : null;

            if(xPoint == null) {
                throw new JsonSyntaxException("Missing required field: \"x\"");
            }
            if(yPoint == null) {
                throw new JsonSyntaxException("Missing required field: \"y\"");
            }
            if(width == null) {
                throw new JsonSyntaxException("Missing required field: \"width\"");
            }
            if(height == null) {
                throw new JsonSyntaxException("Missing required field: \"height\"");
            }

            Graphics2D imageGraphics = image.createGraphics();
            if(color != null) {
                imageGraphics.setColor(color);
            }
            imageGraphics.drawRect(xPoint, yPoint, width, height);
        }));
    }
}
