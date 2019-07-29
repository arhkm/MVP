package com.example.zhafariirsyad.contact.ui.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.zhafariirsyad.contact.R;
import com.example.zhafariirsyad.contact.adapter.AdapterMain;
import com.example.zhafariirsyad.contact.base.BaseActivity;
import com.example.zhafariirsyad.contact.model.Contact;
import com.example.zhafariirsyad.contact.ui.add.Tambah;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView{
    RecyclerView recyclerView;
    AdapterMain adapterMain;
    MainPresenter mainPresenter;
    SwipeRefreshLayout refresh;
    Button tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerTest);
        refresh = findViewById(R.id.refresh);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getContact();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getContact();
            }
        });
        adapterMain = new AdapterMain(this);

        tambah = findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Tambah.class);
                startActivity(i);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterMain);
    }

    @Override
    public void onSuccessLoadContacts(List<Contact> contacts) {
        adapterMain.replace_data(contacts);
        refresh.setRefreshing(false);
    }

    @Override
    public void onShow() {
        refresh.setRefreshing(true);
    }

    @Override
    public void onHide() {

    }

    @Override
    public void getHttp(String http) {
        super.showHttp(http);
    }

    @Override
    public void getError(String error) {
        super.showError(error);
    }
}
