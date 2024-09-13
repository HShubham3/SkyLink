package com.scm.skylink.services.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.entities.UserEntity;
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
    public ContactDto updateContact(Long id, ContactDto contactDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public List<ContactDto> getAllContact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllContact'");
    }

    @Override
    public ContactDto geContactById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geContactById'");
    }

    @Override
    public List<ContactDto> search(String name, String email, String phoneNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public Page<ContactEntity> getContactsByUser(UserEntity user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        Page<ContactEntity> contact = contactsRepo.findByUser(user, pageable);

        return contact;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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

}
