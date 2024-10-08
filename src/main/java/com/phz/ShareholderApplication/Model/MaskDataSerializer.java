package com.phz.ShareholderApplication.Model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MaskDataSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String maskedValue = maskValue(value);
        gen.writeString(maskedValue);
    }

    private String maskValue(String value) {
        if (value == null || value.length() < 2) {
            return "XXX";
        }
        return value.replaceAll(".(?=.{2})", "*");
    }
}
