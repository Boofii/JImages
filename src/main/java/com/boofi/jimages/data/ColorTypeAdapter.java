package com.boofi.jimages.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class ColorTypeAdapter extends TypeAdapter<Color> {

    @Override
    public void write(JsonWriter out, Color value) throws IOException {
        out.beginObject();
        out.name("red").value(value.getRed());
        out.name("green").value(value.getGreen());
        out.name("blue").value(value.getBlue());
        out.endObject();
    }

    @Override
    public Color read(JsonReader in) throws IOException {
        int colorRed = 255;
        int colorGreen = 255;
        int colorBlue = 255;

        in.beginObject();
        while(in.hasNext()) {
            switch(in.nextName()) {
                case "red" -> colorRed = in.nextInt();
                case "green" -> colorGreen = in.nextInt();
                case "blue" -> colorBlue = in.nextInt();
            }
        }
        in.endObject();

        return new Color(colorRed, colorGreen, colorBlue);
    }
}
