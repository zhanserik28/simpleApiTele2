package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Integer id){
        Optional<Contact> foundContact = contactRepository.findById(id);
        return foundContact.orElse(null);
    }

    public void saveOrUpdate(Contact contact){
        contactRepository.save(contact);
    }

    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }

    public void deleteById(Integer id) {
        contactRepository.deleteById(id);
    }

    public void deleteAll() {
        contactRepository.deleteAll();
    }

}
