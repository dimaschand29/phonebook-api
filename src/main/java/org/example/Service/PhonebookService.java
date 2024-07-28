package org.example.Service;

import org.example.Dto.PhonebookDto;
import org.example.Model.Phonebook;
import org.example.Repository.PhonebookRepository;
import org.example.Response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhonebookService {

    @Autowired
    private PhonebookRepository phonebookRepository;

    public ResponseEntity<?> updatePhonebook (Long id, PhonebookDto phonebookDto){
        try {
            Optional <Phonebook> phonebookExistingByIdOpt = phonebookRepository.findById(id);
            if (phonebookExistingByIdOpt.isEmpty()){
                throw new Exception("Data not found");
            }
            Optional <Phonebook> phonebookExistingByPhoneOpt = phonebookRepository.findByPhone(phonebookDto.getPhone());
            if (!phonebookExistingByPhoneOpt.isEmpty() && (phonebookExistingByPhoneOpt.get().getId()!=phonebookExistingByIdOpt.get().getId())){
                throw new Exception("Phone already exist");
            }
            Optional <Phonebook> phonebookExistingByNameOpt = phonebookRepository.findByName(phonebookDto.getName());
            if (!phonebookExistingByNameOpt.isEmpty() && (phonebookExistingByNameOpt.get().getId()!=phonebookExistingByIdOpt.get().getId())){
                throw new Exception("Name already exist");
            }
            Phonebook phonebook = phonebookExistingByIdOpt.get();
            phonebook.setId(id);
            phonebook.setName(phonebookDto.getName());
            phonebook.setPhone(phonebookDto.getPhone());
            if (!ObjectUtils.isEmpty(phonebookDto.getEmail())){
                phonebook.setEmail(phonebookDto.getEmail());
            }
            phonebookRepository.save(phonebook);
            return ResponseEntityBuilder.okResponse(phonebookDto);
        } catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            return ResponseEntityBuilder.errorResponse(msg);
        }
    }

    public ResponseEntity<?> createPhonebook (PhonebookDto phonebookDto){
        try {
            Optional <Phonebook> phonebookExistingByPhoneOpt = phonebookRepository.findByPhone(phonebookDto.getPhone());
            Optional <Phonebook> phonebookExistingByNameOpt = phonebookRepository.findByName(phonebookDto.getName());
            if (!phonebookExistingByPhoneOpt.isEmpty()){
                throw new Exception("Phone already exist");
            }
            if (!phonebookExistingByNameOpt.isEmpty()){
                throw new Exception("Name already exist");
            }
            Phonebook phonebook = new Phonebook();
            phonebook.setName(phonebookDto.getName());
            phonebook.setPhone(phonebookDto.getPhone());
            phonebook.setEmail(phonebookDto.getEmail());
            phonebookRepository.save(phonebook);
            return ResponseEntityBuilder.okResponse(phonebookDto);
        } catch (Exception e){
            String msg = e.getMessage();
            return ResponseEntityBuilder.errorResponse(msg);
        }
    }

    public ResponseEntity<?> getPhonebook (String query){
        try {
            List<Phonebook> phonebookList = new ArrayList<>();
            if (!ObjectUtils.isEmpty(query)){
                phonebookList = phonebookRepository.findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(query,query,query);
            } else {
                phonebookList = phonebookRepository.findAll();
            }
            return ResponseEntityBuilder.okResponse(phonebookList);
        } catch (Exception e){
            String msg = e.getMessage();
            return ResponseEntityBuilder.errorResponse(msg);
        }
    }

    public ResponseEntity<?> getById (Long id){
        try {
            Optional <Phonebook> phonebookExistingByIdOpt = phonebookRepository.findById(id);
            if (phonebookExistingByIdOpt.isEmpty()){
                throw new Exception("Data not found");
            }
            Phonebook phonebook = phonebookExistingByIdOpt.get();
            return ResponseEntityBuilder.okResponse(phonebook);
        } catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            return ResponseEntityBuilder.errorResponse(msg);
        }
    }

    public ResponseEntity<?> deleteById (Long id){
        try {
            Optional <Phonebook> phonebookExistingByIdOpt = phonebookRepository.findById(id);
            if (phonebookExistingByIdOpt.isEmpty()){
                throw new Exception("Data not found");
            }
            phonebookRepository.deleteById(id);
            return ResponseEntityBuilder.okResponse(phonebookExistingByIdOpt.get());
        } catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            return ResponseEntityBuilder.errorResponse(msg);
        }
    }
}
