package com.betall.app.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by fly on 2018/1/29.
 */

public class ApiInterceptor implements Interceptor {

    private static final String UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        String method = chain.request().method().toUpperCase();

        if ("GET".equals(method))
            return interceptGET(chain);

        if ("POST".equals(method))
            return interceptPOST(chain);

        return chain.proceed(chain.request());
    }

    private Response interceptGET(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = makeNewUrl(request.url());
        request = request.newBuilder()
                .url(url)
                .addHeader("User-Agent", UA)
                .build();
        return chain.proceed(request);
    }

    private HttpUrl makeNewUrl(HttpUrl oldUrl) {
        HttpUrl.Builder urlBuilder = oldUrl.newBuilder();
        for (Map.Entry<String, String> entry : defaultFields().entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return urlBuilder.build();
    }

    private Response interceptPOST(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = makeNewBody(request.body());
        request = request.newBuilder()
                .addHeader("User-Agent", UA)
                .method(request.method(), body)
                .build();
        return chain.proceed(request);
    }

    private RequestBody makeNewBody(RequestBody oldBody) throws IOException {
        Buffer buffer = new Buffer();
        oldBody.writeTo(buffer);

        String string = "";
        for (Map.Entry<String, String> entry : defaultFields().entrySet()) {
            string += ("&" + entry.getKey() + "=" + entry.getValue());
        }
        buffer.writeString(string, UTF_8);

        RequestBody body = RequestBody.create(MEDIA_TYPE, buffer.readByteArray());
        return body;
    }

    private HashMap<String, String> defaultFields() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("device_id", "afjklsafjklsadjfklsjdfklsajdklf");
        fields.put("token", "23232k3j223kjk3j2kjk34j24k3h4h");
        return fields;
    }
}
