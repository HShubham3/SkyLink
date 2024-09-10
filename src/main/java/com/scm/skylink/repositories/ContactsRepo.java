package com.scm.skylink.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.skylink.entities.ContactEntity;

public interface ContactsRepo extends JpaRepository<ContactEntity, Long> {

}
