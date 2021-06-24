package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts =  contactService.findAll();

        if(contacts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") int id) {
        Contact contact = contactService.findById( id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<String> createContact(@RequestBody Contact contact) {
        try {
            contactService.saveOrUpdate(contact);
            return new ResponseEntity<>("successfully created contact", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<HttpStatus> updateContact(@PathVariable("id") int id, @RequestBody Contact updatedContact) {
        Contact contact = contactService.findById(id);

        if (contact != null) {
            contact.setFirstName(updatedContact.getFirstName());
            contact.setLastName(updatedContact.getLastName());
            contact.setDate(updatedContact.getDate());
            contact.setNumber(updatedContact.getNumber());
            contact.setAddress(updatedContact.getAddress());
            contact.setGender(updatedContact.getGender());
            contactService.saveOrUpdate(contact);
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
        try {
            contactService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            contactService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
