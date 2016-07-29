package com.example.errors;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by retor on 15.03.2016.
 */
public class ErrorHandler {

    private Converter<ResponseBody, ServerError> converter;

    public ErrorHandler(Converter<ResponseBody, ServerError> converter) {
        this.converter = converter;
    }

    public ServerError parseError(Response<?> response) {
        ServerError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ServerError();
        }
        return error;
    }
}
