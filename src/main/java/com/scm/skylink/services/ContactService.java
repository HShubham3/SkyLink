package com.scm.skylink.services;

import java.util.List;

import com.scm.skylink.dto.ContactDto;

public interface ContactService {

    // save contact

    ContactDto saveContact(ContactDto contactDto);

    // update contact

    ContactDto updateContact(Long id, ContactDto contactDto);

    // get all contacts

    List<ContactDto> getAllContact();

    // get contact by id

    ContactDto geContactById(Long id);

    // search contact

    List<ContactDto> search(String name, String email, String phoneNo);

    // get contact by userId

    List<ContactDto> getContactsByUserId(String id);

    // delete by id

    void delete(int id);
}
