package com.ltp.contacts.service;

import java.util.List;
import java.util.stream.IntStream;

import com.ltp.contacts.exception.NotFoundException;
import com.ltp.contacts.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.contacts.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
  @Autowired private ContactRepository contactRepository;

  private int findIndexById(String id) {
    return IntStream.range(0, contactRepository.getContacts().size())
        .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public Contact getContactById(String id) {
    return contactRepository.getContact(findIndexById(id));
  }

  @Override
  public List<Contact> listContacts() {
    return contactRepository.getContacts();
  }

  @Override
  public void createContact(Contact contact) {
    contactRepository.saveContact(contact);
  }

  @Override
  public Contact updateContact(Contact contact, String id) {
    Contact c1 = this.getContactById(id);
    int index = this.findIndexById(id);
    c1.setName(contact.getName());
    c1.setPhoneNumber(contact.getPhoneNumber());
    this.contactRepository.updateContact(index, c1);
    return this.getContactById(id);
  }

  @Override
  public void deleteContact(String id) {
    this.contactRepository.deleteContact(this.findIndexById(id));
  }
}
