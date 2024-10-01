package com.phz.ShareholderApplication.Model;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) // Apply this annotation to fields only
@JacksonAnnotationsInside
@JsonSerialize(using = MaskDataSerializer.class) // Specify the custom serializer
public @interface MaskData {

}
