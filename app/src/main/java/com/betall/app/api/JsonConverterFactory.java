package com.betall.app.api;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by fly on 2018/1/27.
 */

public final class JsonConverterFactory extends Converter.Factory {

    public static JsonConverterFactory create() {
        return new JsonConverterFactory();
    }

    private final Gson gson;

    private JsonConverterFactory() {
        this.gson = new Gson();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return value -> {
            String json = value.string();
            Type resultType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };

            Result result = gson.fromJson(json, resultType);
            if (result.code == 0) {
                return result.data;
            } else {
                throw new RuntimeException("code: " + result.code);
            }
        };
    }
}

