package com.erayarslan.publeak.util;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Streaming;

public interface TestImage {
    @Streaming
    @GET("/thumbs/{code}")
    Response getImage(@Path("code") String code);
}
