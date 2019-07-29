package com.example.zhafariirsyad.contact.ui.main;

import com.example.zhafariirsyad.contact.base.BaseView;
import com.example.zhafariirsyad.contact.model.Contact;

import java.util.List;

public interface MainView extends BaseView{
    void onSuccessLoadContacts(List<Contact> contacts);
}
