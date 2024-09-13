package com.scm.skylink.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.entities.UserEntity;

public interface ContactsRepo extends JpaRepository<ContactEntity, Long> {

    Page<ContactEntity> findByUser(UserEntity user, Pageable pageable);

    Page<ContactEntity> findByUserAndNameContaining(UserEntity user, String name, Pageable pageable);

    Page<ContactEntity> findByUserAndPhoneNoContaining(UserEntity user, String phoneNo, PageRequest pageable);

    Page<ContactEntity> findByUserAndEmailContaining(UserEntity user, String email, PageRequest pageable);

}
