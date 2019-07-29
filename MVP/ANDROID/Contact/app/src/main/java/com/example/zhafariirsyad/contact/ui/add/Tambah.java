package com.example.zhafariirsyad.contact.ui.add;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhafariirsyad.contact.R;
import com.example.zhafariirsyad.contact.base.BaseActivity;
import com.example.zhafariirsyad.contact.ui.main.MainActivity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Tambah extends BaseActivity implements TambahView {
    EditText nama, email, phone, photo;
    Button btnsave, btnTampildata ,btnupdate, btnhapus, btnAvatar;
    CircularImageView avatar;
    TambahPresenter presenter;
    String part_image;
    final int REQUEST_GALERY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        nama     = findViewById(R.id.edt_nama);
        email     = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        photo = findViewById(R.id.edt_photo);
        btnupdate = findViewById(R.id.btnUpdate);
        btnTampildata = findViewById(R.id.btntampildata);
        btnhapus = findViewById(R.id.btnHapus);
        btnsave  = findViewById(R.id.btn_insertdata);
        avatar = findViewById(R.id.avatar);
        btnAvatar = findViewById(R.id.btn_avatar);

        presenter = new TambahPresenter(this);

        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(i, "Open Gallery"), REQUEST_GALERY);
            }
        });

        btnTampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snama, semail, sphone;
                snama = nama.getText().toString();
                semail = email.getText().toString();
                sphone = phone.getText().toString();
                if (snama.isEmpty() || semail.isEmpty() || sphone.isEmpty()){
                    Toast.makeText(Tambah.this, "Harap lengkapi form", Toast.LENGTH_SHORT).show();
                }else{
                    presenter.insert(snama,semail,sphone,part_image);
                }
            }
        });

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if (iddata != null){
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            btnhapus.setVisibility(View.VISIBLE);
            btnupdate.setVisibility(View.VISIBLE);
            btnAvatar.setText("Change Avatar");

            String propic = data.getStringExtra("photo");

            Picasso.get().load("http://192.168.43.65/Laravel/laravel-api-basic/public/img/profile/" + propic).into(avatar);

            nama.setText(data.getStringExtra("nama"));
            email.setText(data.getStringExtra("email"));
            phone.setText(data.getStringExtra("phone"));
            photo.setText(data.getStringExtra("photo"));
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snama, semail, sphone;
                snama = nama.getText().toString();
                semail = email.getText().toString();
                sphone = phone.getText().toString();
                if (snama.isEmpty() || semail.isEmpty() || sphone.isEmpty()){
                    Toast.makeText(Tambah.this, "Harap lengkapi form", Toast.LENGTH_SHORT).show();
                }else{
                    presenter.update(iddata,snama,semail,sphone,part_image);
                }
            }
        });

        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete(iddata);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQUEST_GALERY)
            {
                Uri dataImage = data.getData();
                String[] imageAry = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataImage, imageAry, null, null, null);

                if (cursor != null)
                {
                    cursor.moveToFirst();
                    int indexImg = cursor.getColumnIndex(imageAry[0]);
                    part_image = cursor.getString(indexImg);

                    if (part_image != null)
                    {
                        File image = new File(part_image);
                        avatar.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                    }
                }

            }
        }
    }

    @Override
    public void onSuccess(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Tambah.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onShow() {
        super.showLoading();
    }

    @Override
    public void onHide() {
        super.dismissLoading();
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
