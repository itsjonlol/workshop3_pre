package com.workshop3_pre.workshop3_pre.repository;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.workshop3_pre.workshop3_pre.model.User;

@Repository
public class ContactRepo {

    @Autowired
    private RedisTemplate<String,Object> template;

    public void save(User user) {

        String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String randomString = generateRandom(random);
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(randomString);
        }
        template.opsForHash().put("User", user.getId(), user);  // Save contact using its ID as the key
    }

    public String generateRandom(String aToZ) {
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < 8; i++) {
        int randIndex=rand.nextInt(aToZ.length()); 
        res.append(aToZ.charAt(randIndex));            
        }
        return res.toString();
    }

    public User getUserById(String id) {
        return (User) template.opsForHash().get("User",id);
    }

    public Boolean checkUserExist(String id) {
        return template.opsForHash().hasKey("User",id);
    }

    public List<Object> getAllUsers() {
        List<Object> objectList = template.opsForHash().values("User");
        System.out.println("Repo list has " + objectList.size());
        return objectList;
    }

}   
