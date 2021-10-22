package com.boofi.jimages.image;

import com.boofi.jimages.image.property.ImageProperty;
import com.boofi.jimages.image.property.ImagePropertyRegistry;
import com.boofi.jimages.data.ColorTypeAdapter;
import com.boofi.jimages.util.ResourceManager;
import com.google.gson.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageManager extends ResourceManager {

    private static final Gson GSON = (new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(Color.class, new ColorTypeAdapter())).create();

    public ImageManager() {
        super("images");
    }

    @Override
    protected void readResources(Map<File, JsonElement> loader) {
        loader.forEach((file, jsonElement) -> {
            try {
                if(jsonElement instanceof JsonObject mainObject) {
                    Color imageColor = mainObject.has("fill_color") ? GSON.fromJson(mainObject.get("fill_color"), Color.class): null;
                    Integer imageWidth = mainObject.has("width") ? mainObject.get("width").getAsInt() : null;
                    Integer imageHeight = mainObject.has("height") ? mainObject.get("height").getAsInt() : null;

                    if(imageWidth == null) {
                        throw new JsonSyntaxException("Missing required field: \"width\"");
                    }
                    if(imageHeight == null) {
                        throw new JsonSyntaxException("Missing required field: \"height\"");
                    }

                    BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
                    if(imageColor != null) {
                        for(int x = 0; x < image.getWidth(); x++) {
                            for(int y = 0; y < image.getHeight(); y++) {
                                image.setRGB(x, y, imageColor.getRGB());
                            }
                        }
                    }
                    if(mainObject.has("properties")) {
                        for(JsonElement imagePropertyElement : mainObject.getAsJsonArray("properties")) {
                            if(imagePropertyElement instanceof JsonObject imagePropertyJson) {
                                ImageProperty imageProperty = ImagePropertyRegistry.getById(imagePropertyJson.get("type").getAsString());
                                imageProperty.getAction().accept(imagePropertyJson, image);
                            } else {
                                throw new JsonSyntaxException("Expected field \"properties\" to be a JSON object");
                            }
                        }
                    }
                    File imageFile = new File(""+file.getPath()+".png");
                    if(imageFile.createNewFile()) {
                        ImageIO.write(image, "png", imageFile);
                    }
                }
            } catch(IOException ignored) {

            }
        });
    }
}
