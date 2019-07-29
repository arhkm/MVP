package com.example.zhafariirsyad.contact.ui.add;

import com.example.zhafariirsyad.contact.base.BasePresenter;
import com.example.zhafariirsyad.contact.base.BaseResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class TambahPresenter<viewtambah extends TambahView> extends BasePresenter {

    viewtambah tambahView;

    public TambahPresenter(viewtambah tambahView) {
        this.tambahView = tambahView;
    }

    public void insert(String nama, String email, String phone, String photo)
    {
        tambahView.onShow();

        RequestBody Nnama = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody Nemail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody Nphone = RequestBody.create(MediaType.parse("text/plain"), phone);

        MultipartBody.Part partPhoto = null;

        if (photo != null) {
            File avatar = new File(photo);
            RequestBody reqPhoto = RequestBody.create(MediaType.parse("multipart/form-file"), avatar);
            partPhoto = MultipartBody.Part.createFormData("photo", avatar.getName(), reqPhoto);
        }

        apiClass.insertCon(Nnama,Nemail,Nphone,partPhoto).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                tambahView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful()) {
                    tambahView.onSuccess(response.body().getStatus());
                    tambahView.onHide();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                tambahView.getError(t.getMessage());
                tambahView.onHide();
            }
        });
    }

    public void update(String id, String nama, String email, String phone, String photo)
    {
        tambahView.onShow();

        RequestBody Nid = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody Nnama = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody Nemail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody Nphone = RequestBody.create(MediaType.parse("text/plain"), phone);

        MultipartBody.Part partPhoto = null;

        if (photo != null) {
            File avatar = new File(photo);
            RequestBody reqPhoto = RequestBody.create(MediaType.parse("multipart/form-file"), avatar);
            partPhoto = MultipartBody.Part.createFormData("photo", avatar.getName(), reqPhoto);
        }

        apiClass.updateCon(Nid,Nnama,Nemail,Nphone,partPhoto).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                tambahView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful()) {
                    tambahView.onSuccess(response.body().getStatus());
                    tambahView.onHide();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                tambahView.getError(t.getMessage());
                tambahView.onHide();
            }
        });
    }

    public void delete(String id)
    {
        tambahView.onShow();
        apiClass.deleteCon(id).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                tambahView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful()) {
                    tambahView.onSuccess(response.body().getStatus());
                    tambahView.onHide();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                tambahView.getError(t.getMessage());
                tambahView.onHide();
            }
        });
    }
}
