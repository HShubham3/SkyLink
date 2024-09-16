package com.scm.skylink.services.imp;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.exceptions.ResourceNotFoundException;
import com.scm.skylink.repositories.ContactsRepo;
import com.scm.skylink.repositories.UserRepo;
import com.scm.skylink.services.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements ContactService {

    private final ContactsRepo contactsRepo;

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    @Override
    public ContactDto saveContact(ContactDto contactDto) {
        return modelMapper.map(contactsRepo.save(modelMapper.map(contactDto, ContactEntity.class)), ContactDto.class);
    }

    @Override
    public ContactDto updateContact(Long id, ContactDto contactNew) {
        var contactOld = contactsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found "));

        contactOld.setName(contactNew.getName());
        contactOld.setEmail(contactNew.getEmail());
        contactOld.setPhoneNo(contactNew.getPhoneNo());
        contactOld.setAddress(contactNew.getAddress());
        contactOld.setDescription(contactNew.getDescription());
        contactOld.setFavorite(contactNew.isFavorite());
        contactOld.setWebLink(contactNew.getWebLink());
        contactOld.setLinkedinLink(contactNew.getLinkedinLink());
        contactOld.setLinks(contactNew.getLinks());

        contactOld.setContactImageUrl(contactNew.getContactImageUrl());

        return modelMapper.map(contactsRepo.save(contactOld), ContactDto.class);

    }

    @Override
    public ContactDto getContactById(Long id) {
        return modelMapper.map(contactsRepo.findById(id), ContactDto.class);
    }

    @Override
    public Page<ContactEntity> getContactsByUser(UserEntity user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        Page<ContactEntity> contact = contactsRepo.findByUser(user, pageable);

        return contact;
    }

    @Override
    public Page<ContactEntity> searchByName(String name, int page, int size, String sortBy, String direction,
            UserEntity user) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        Page<ContactEntity> contacts = contactsRepo.findByUserAndNameContaining(user, name, pageable);

        return contacts;

    }

    @Override
    public Page<ContactEntity> searchByPhoneNo(String phoneNo, int page, int size, String sortBy, String direction,
            UserEntity user) {
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        Page<ContactEntity> contacts = contactsRepo.findByUserAndPhoneNoContaining(user, phoneNo, pageable);
        contactsRepo.deleteById(1l);
        return contacts;
    }

    @Override
    public Page<ContactEntity> searchByEmail(String email, int page, int size, String sortBy, String direction,
            UserEntity user) {
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        Page<ContactEntity> contacts = contactsRepo.findByUserAndEmailContaining(user, email, pageable);

        return contacts;
    }

    @Override
    public void deleteContactById(long id) {
        ContactEntity contact = contactsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact with id " + id + " not found"));

        contactsRepo.deleteByIdCustom(id);
        System.out.println("Successfully deleted...");
    }

}
