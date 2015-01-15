package com.erayarslan.publeak.util;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Streaming;

public interface TestImage {
    @Streaming
    @GET("/DvpvklR.png")
    void getImage(Callback<Response> callback);
}
