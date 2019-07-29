package com.example.zhafariirsyad.contact.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhafariirsyad.contact.R;
import com.example.zhafariirsyad.contact.model.Contact;
import com.example.zhafariirsyad.contact.ui.add.Tambah;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.Holder> {
    List<Contact> contacts = new ArrayList<>();
    Context context;
    View view;

    public AdapterMain(Context context) {
        this.context = context;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_email;
        CircularImageView tv_avatar;
        CardView cv_contact;
        public Holder(View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_avatar = itemView.findViewById(R.id.tv_avatar);
            cv_contact = itemView.findViewById(R.id.cv_item);
        }
    }

    @Override
    public AdapterMain.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(final AdapterMain.Holder holder, final int position) {
        holder.tv_nama.setText(contacts.get(position).getNama());
        holder.tv_email.setText(contacts.get(position).getEmail());
        Picasso.get()
                .load("http://192.168.43.65/Laravel/laravel-api-basic/public/img/profile/"
                        + contacts.get(position).getPhoto()).into(holder.tv_avatar);
        holder.cv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Tambah.class);
                i.putExtra("id", contacts.get(position).getId());
                i.putExtra("nama", contacts.get(position).getNama());
                i.putExtra("email", contacts.get(position).getEmail());
                i.putExtra("phone", contacts.get(position).getPhone());
                i.putExtra("photo", contacts.get(position).getPhoto());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public void replace_data(List<Contact> contacts)
    {
        this.contacts = contacts;
        notifyDataSetChanged();
    }
}
