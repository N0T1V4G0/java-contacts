package com.ltp.contacts.web;

import com.ltp.contacts.exception.NotFoundException;
import com.ltp.contacts.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;

import com.ltp.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

  @Autowired private ContactService contactService;

  @GetMapping("/contact/{id}")
  private ResponseEntity<Contact> getContactById(@PathVariable String id) {
    try {
      Contact c = contactService.getContactById(id);
      return new ResponseEntity<>(c, HttpStatus.OK);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/contact")
  private ResponseEntity<List<Contact>> listContacts() {
    return new ResponseEntity<>(contactService.listContacts(), HttpStatus.OK);
  }

  @PostMapping("/contact")
  private ResponseEntity<HttpStatus> createContact(@RequestBody Contact contact) {
    contactService.createContact(contact);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/contact/{id}")
  private ResponseEntity<Contact> updateContact(
      @RequestBody Contact contact, @PathVariable String id) {
    try {
      Contact updatedContact = contactService.updateContact(contact, id);
      return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("contact/{id}")
  private ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
    try {
      contactService.deleteContact(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
