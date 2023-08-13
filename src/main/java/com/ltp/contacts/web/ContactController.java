package com.ltp.contacts.web;

import com.ltp.contacts.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;

import com.ltp.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    private ResponseEntity<Contact> getContactById(@PathVariable String id) {
        Contact c = contactService.getContactById(id);
        return new ResponseEntity<Contact>(c, HttpStatus.OK);
    }

    @GetMapping("/contact")
    private ResponseEntity<List<Contact>> listContacts() {
        return new ResponseEntity<List<Contact>>(contactService.listContacts(), HttpStatus.OK);
    }
}
