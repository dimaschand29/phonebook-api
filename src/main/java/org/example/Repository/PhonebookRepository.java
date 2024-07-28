package org.example.Repository;

import org.example.Model.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {
    Optional<Phonebook> findById (Long id);
    Optional<Phonebook> findByPhone (String phone);
    Optional<Phonebook> findByName (String name);
    List<Phonebook> findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase (
            String name, String phone, String email
    );
}
