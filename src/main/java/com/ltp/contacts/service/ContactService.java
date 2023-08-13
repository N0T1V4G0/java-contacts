package com.ltp.contacts.service;

import com.ltp.contacts.pojo.Contact;

import java.util.List;

public interface ContactService {
    public Contact getContactById(String id);
    public List<Contact> listContacts();
}
