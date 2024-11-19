package com.workshop3_pre.workshop3_pre.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop3_pre.workshop3_pre.model.User;
import com.workshop3_pre.workshop3_pre.repository.ContactRepo;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepo contactRepo;
    
    public void saveUser(User user) {
        contactRepo.save(user);
    }
    public User getUserById(String id) {
        return contactRepo.getUserById(id);
    }
    public Boolean checkUserExist(String id) {
        return contactRepo.checkUserExist(id);
    }
    public List<User> getAllUsers() {
        List<Object> objects = contactRepo.getAllUsers();
        List<User> users = objects.stream()
                              .filter(obj -> obj instanceof User) // Ensure it's a User instance
                              .map(obj -> (User) obj) // Cast to User
                              .collect(Collectors.toList());
        System.out.println("Service list has size: " + users.size());
        return users;
        
    }
      
}
