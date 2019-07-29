package com.example.zhafariirsyad.contact.model;

public class Contact {
    String id,nama,email,phone,photo;

    public Contact(String id, String nama, String email, String phone, String photo) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }
}
