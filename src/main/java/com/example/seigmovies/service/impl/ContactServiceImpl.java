package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Contact;
import com.example.seigmovies.mapper.ContactMapper;
import com.example.seigmovies.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public int addContact(Contact contact) {
        return contactMapper.addContact(contact);
    }
}
