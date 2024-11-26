package com.workshop3_pre.workshop3_pre.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.workshop3_pre.workshop3_pre.model.User;
import com.workshop3_pre.workshop3_pre.util.Util;

@Repository
public class ContactRepo {

    @Autowired
    @Qualifier(Util.template01)
    RedisTemplate<String, String> template;
    //private RedisTemplate<String,Object> template;

    // public void save(User user) {

        
    //     template.opsForHash().put("User", user.getId(), user);  // Save contact using its ID as the key
    // }


    // public User getUserById(String id) {
    //     return (User) template.opsForHash().get("User",id);
    // }

    // public Boolean checkUserExist(String id) {
    //     return template.opsForHash().hasKey("User",id);
    // }

    // public List<Object> getAllUsers() {
    //     List<Object> objectList = template.opsForHash().values("User");
    //     System.out.println("Repo list has " + objectList.size());
    //     return objectList;
    // }
    public void save(User user) {
        System.out.println("From repo: user id is " + user.getId());
        String[] userInfo = user.toString().split(",");
        System.out.println(userInfo[0]);

        template.opsForHash().put("User", userInfo[0], user.toString());  // Save contact using its ID as the key
    }


    public User getUserById(String id) {
        String data = (String) template.opsForHash().get("User",id);
        String[] rawData = data.split(",");

        String name = rawData[1];
        String email = rawData[2];
        String phoneNumber = rawData[3];
        String dob = rawData[4];
        LocalDate dateOfBirth = LocalDate.parse(dob);
        
        User user = new User(id,name,email,phoneNumber,dateOfBirth);
        return user;



    }

    public Boolean checkUserExist(String id) {
        return template.opsForHash().hasKey("User",id);
    }
    public Boolean deleteUser(String id) {
        Boolean isDeleted = false;
        Long iFound = template.opsForHash().delete("User", id);
        if (iFound>0) {
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Object> getAllUsers() {
        List<Object> objectList = template.opsForHash().values("User");
        System.out.println("Repo list has " + objectList.size());
        return objectList;
    }

    public void updateUser(User user) {
        // Boolean isUpdated = false;
        String userId = user.getId();
        template.opsForHash().put("User", userId, user.toString());

    }

  
        

    

}   
