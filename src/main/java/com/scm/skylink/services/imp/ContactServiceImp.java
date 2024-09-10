package com.scm.skylink.services.imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.repositories.ContactsRepo;
import com.scm.skylink.services.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements ContactService {

    private final ContactsRepo contactsRepo;

    private final ModelMapper modelMapper;

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
    public List<ContactDto> getContactsByUserId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContactsByUserId'");
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
