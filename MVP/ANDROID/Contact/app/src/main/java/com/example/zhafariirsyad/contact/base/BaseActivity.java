package com.example.zhafariirsyad.contact.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog pd;
    public void showLoading()
    {
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
    }
    public void dismissLoading()
    {
        pd.dismiss();
    }
    public void showError(String error)
    {
        Log.e("ERROR", "showError: " + error );
    }
    public void showHttp(String http)
    {
        Log.d("HTTP", "showHttp: " + http );
    }


}
