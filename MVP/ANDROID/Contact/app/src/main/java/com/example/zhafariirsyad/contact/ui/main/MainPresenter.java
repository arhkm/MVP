package com.example.zhafariirsyad.contact.ui.main;

import com.example.zhafariirsyad.contact.base.BasePresenter;
import com.example.zhafariirsyad.contact.response.MainResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter<MV extends MainView> extends BasePresenter{
    MV mainview;

    public MainPresenter(MV mainview) {
        this.mainview = mainview;
    }

    public void getContact()
    {
        mainview.onShow();
        apiClass.getContact().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                mainview.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful()){
                    mainview.onSuccessLoadContacts(response.body().getContacts());
                    mainview.onHide();
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mainview.getError(t.getMessage());
                mainview.onHide();
            }
        });
    }

}
