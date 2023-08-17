package com.ltp.contacts.service;

import com.ltp.contacts.pojo.Contact;

import java.util.List;

public interface ContactService {
  Contact getContactById(String id);

  List<Contact> listContacts();

  void createContact(Contact contact);

  Contact updateContact(Contact contact, String id);

  void deleteContact(String id);
}
