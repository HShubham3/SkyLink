package com.scm.skylink.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.entities.UserEntity;

public interface ContactService {

        // save contact

        ContactDto saveContact(ContactDto contactDto);

        // update contact

        ContactDto updateContact(Long id, ContactDto contactDto);

        // get contact by id

        ContactDto getContactById(Long id);

        // get all contact by user

        Page<ContactEntity> getContactsByUser(UserEntity user, int page, int size, String sortBy, String direction);

        // search contact

        Page<ContactEntity> searchByName(String name, int page, int size, String sortBy, String direction,
                        UserEntity user);

        Page<ContactEntity> searchByPhoneNo(String phoneNo, int page, int size, String sortBy, String direction,
                        UserEntity user);

        Page<ContactEntity> searchByEmail(String email, int page, int size, String sortBy, String direction,
                        UserEntity user);

        // delete

        void deleteContactById(long id);

}
