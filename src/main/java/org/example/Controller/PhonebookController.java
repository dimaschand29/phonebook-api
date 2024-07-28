package org.example.Controller;

import org.example.Dto.PhonebookDto;
import org.example.Service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/contacts")
public class PhonebookController {

    @Autowired
    private PhonebookService phonebookService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getById (@PathVariable("id") Long id){
        return phonebookService.getById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> createPhoneBook (@RequestBody PhonebookDto phonebookDto){
        return phonebookService.createPhonebook(phonebookDto);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getPhonebook (@RequestParam(value = "query", required = false) String query){
        String queryTemp = query;
        return phonebookService.getPhonebook(queryTemp);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> updatePhonebook (@PathVariable("id") Long id, @RequestBody PhonebookDto phonebookDto){
        return phonebookService.updatePhonebook(id, phonebookDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> deletePhonebook (@PathVariable("id") Long id){
        return phonebookService.deleteById(id);
    }
}
