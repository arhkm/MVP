package com.example.zhafariirsyad.contact.network;

import com.example.zhafariirsyad.contact.base.BaseResponse;
import com.example.zhafariirsyad.contact.response.MainResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiClass {
    @GET("contact/show")
    Call<MainResponse> getContact();

    @Multipart
    @POST("contact/insert")
    Call<BaseResponse> insertCon(@Part("nama") RequestBody nama,
                                 @Part("email") RequestBody email,
                                 @Part("phone") RequestBody phone,
                                 @Part MultipartBody.Part photo);

    @Multipart
    @POST("contact/update")
    Call<BaseResponse> updateCon(@Part("id") RequestBody id,
                                 @Part("nama") RequestBody nama,
                                 @Part("email") RequestBody email,
                                 @Part("phone") RequestBody phone,
                                 @Part MultipartBody.Part photo);

    @DELETE("contact/delete/{id}")
    Call<BaseResponse> deleteCon(@Path("id") String id);
}
