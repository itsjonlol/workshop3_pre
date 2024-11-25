package com.workshop3_pre.workshop3_pre.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    
    public Boolean deleteUser(String id) {
        return contactRepo.deleteUser(id);
    }

    public List<User> getAllUsers() {
        List<Object> objects = contactRepo.getAllUsers();
        // System.out.println("Object list has size " + objects.size());
        // List<User> users = objects.stream()
        //                       .filter(obj -> obj instanceof User) // Ensure it's a User instance
        //                       .map(obj -> (User) obj) // Cast to User
        //                       .collect(Collectors.toList());
        // System.out.println("Service list has size: " + users.size());

        List<User> users = new ArrayList<>();
       
        for (Object data : objects) {
            String[] rawData = ((String) data).split(",");
            String id = rawData[0];
            String name = rawData[1];
            String email = rawData[2];
            String phoneNumber = rawData[3];
            String dob = rawData[4];
            LocalDate dateOfBirth = LocalDate.parse(dob);
            
            User user = new User(id,name,email,phoneNumber,dateOfBirth);
            users.add(user);
        }
        System.out.println("Service list has size: " + users.size());
        
        return users;
        
    }

    public Boolean checkIfNameExists(String name) {
        List<User> users = this.getAllUsers();
        for (User user : users) {
            if(user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkIfEmailExists(String email) {
        List<User> users = this.getAllUsers();
        for (User user : users) {
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
      
}
