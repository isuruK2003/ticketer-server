package me.ticketing_system;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ValidationConstraintDeserializer implements JsonDeserializer<ValidationConstraint> {
    @Override
    public ValidationConstraint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String fieldName = jsonObject.get("fieldName").getAsString();
        Integer minValue = jsonObject.get("minValue").getAsInt();
        Integer maxValue = jsonObject.get("maxValue").getAsInt();

        return new ValidationConstraint(fieldName, minValue, maxValue);
    }
}
