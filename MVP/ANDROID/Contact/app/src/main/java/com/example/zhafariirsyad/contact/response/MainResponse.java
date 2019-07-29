package com.example.zhafariirsyad.contact.response;

import com.example.zhafariirsyad.contact.model.Contact;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {
    @SerializedName("data")
    List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }
}
