package com.example.zhafariirsyad.contact.base;

import com.example.zhafariirsyad.contact.network.ApiClass;
import com.example.zhafariirsyad.contact.network.ApiClient;

import retrofit2.Retrofit;

public class BasePresenter {
    public ApiClass apiClass = new ApiClient().getClient().create(ApiClass.class);
}
