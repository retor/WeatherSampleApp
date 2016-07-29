package com.example.errors;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by retor on 20.03.2016.
 */
public class ErrorAdapter implements JsonDeserializer<ServerError> {

    @Override
    public ServerError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            int code = 0;
            String mes = "Ooppps...";
            if (json.getAsJsonObject().get("statusCode") != null) {
                code = json.getAsJsonObject().get("statusCode").getAsInt();
                mes = json.getAsJsonObject().get("message").getAsString();
            } else if (json.getAsJsonObject().get("non_field_errors") != null){
                mes = json.getAsJsonObject().get("non_field_errors").getAsString();
                code = 500;
            }else if (json.getAsJsonObject().get("detail") != null) {
                mes = json.getAsJsonObject().get("detail").getAsString();
                code = 400;
            }
            return new ServerError(code, mes);
        }else{
            return new ServerError(0, null);
        }
    }
}
