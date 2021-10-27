package com.boofi.jimages.image;

import com.boofi.jimages.image.property.ImageProperty;
import com.boofi.jimages.image.property.ImagePropertyRegistry;
import com.boofi.jimages.resource.JsonResourceManager;
import com.google.gson.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

public class ImageResourceManager extends JsonResourceManager {

    private static final Gson GSON = (new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(Color.class, new ColorTypeAdapter().nullSafe())).create();

    public ImageResourceManager() {
        super("images");
    }

    @Override
    protected void readJsonResources(Map<String, JsonElement> loader) {
        loader.forEach((string, jsonElement) -> {
            if(jsonElement instanceof JsonObject mainObject) {
                try {
                    File imageFile = new File(string + ".png");
                    if(imageFile.createNewFile()) {
                        Color imageFillColor = mainObject.has("fill_color") ? GSON.fromJson(mainObject.get("fill_color"), Color.class) : null;
                        Integer imageWidth = mainObject.has("width") ? mainObject.get("width").getAsInt() : null;
                        Integer imageHeight = mainObject.has("height") ? mainObject.get("height").getAsInt() : null;

                        if(imageWidth == null) {
                            throw new JsonSyntaxException("Missing a required field \"width\", Expected to find an Integer.");
                        }
                        if(imageHeight == null) {
                            throw new JsonSyntaxException("Missing a required field \"height\", Expected to find an Integer.");
                        }

                        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
                        if(imageFillColor != null) {
                            for(int x = 0; x < image.getWidth(); x++) {
                                for(int y = 0; y < image.getHeight(); y++) {
                                    image.setRGB(x, y, imageFillColor.getRGB());
                                }
                            }
                        }
                        if(mainObject.has("properties")) {
                            for(JsonElement imagePropertyElement : mainObject.getAsJsonArray("properties")) {
                                if(imagePropertyElement instanceof JsonObject imagePropertyJson) {
                                    String propertyType = imagePropertyJson.has("type") ? imagePropertyJson.get("type").getAsString() : null;

                                    if(propertyType == null) {
                                        throw new JsonSyntaxException("Missing a required field \"type\", Expected to find a String.");
                                    }

                                    ImageProperty property = ImagePropertyRegistry.get(propertyType);
                                    property.getAction().accept(imagePropertyJson, image);
                                }
                            }
                        }
                        ImageIO.write(image, "png", imageFile);
                    }
                } catch(Exception e) {
                    System.out.println("Error reading image resources in file " + string + ": " + e.getMessage());
                }
            }
        });
    }
}
