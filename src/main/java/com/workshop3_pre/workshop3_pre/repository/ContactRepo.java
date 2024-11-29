package com.workshop3_pre.workshop3_pre.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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

    // public void save(User user) {
    //     System.out.println("From repo: user id is " + user.getId());
    //     String[] userInfo = user.toString().split(",");
    //     System.out.println(userInfo[0]);

    //     template.opsForHash().put("User", userInfo[0], user.toString());  // Save contact using its ID as the key
    // }


    // public User getUserById(String id) {
    //     String data = (String) template.opsForHash().get("User",id);
    //     String[] rawData = data.split(",");

    //     String name = rawData[1];
    //     String email = rawData[2];
    //     String phoneNumber = rawData[3];
    //     String dob = rawData[4];
    //     LocalDate dateOfBirth = LocalDate.parse(dob);
        
    //     User user = new User(id,name,email,phoneNumber,dateOfBirth);
    //     return user;



    // }

    // public Boolean checkUserExist(String id) {
    //     return template.opsForHash().hasKey("User",id);
    // }
    // public Boolean deleteUser(String id) {
    //     Boolean isDeleted = false;
    //     Long iFound = template.opsForHash().delete("User", id);
    //     if (iFound>0) {
    //         isDeleted = true;
    //     }
    //     return isDeleted;
    // }

    // public List<Object> getAllUsers() {
    //     List<Object> objectList = template.opsForHash().values("User");
    //     System.out.println("Repo list has " + objectList.size());
    //     return objectList;
    // }

    // public void updateUser(User user) {
    //     // Boolean isUpdated = false;
    //     String userId = user.getId();
    //     template.opsForHash().put("User", userId, user.toString());

    // }

    // Add or update a single key-value pair in a hash (CREATE)
    public void setHash(String redisKey, String mapKey, String value) {
        template.opsForHash().put(redisKey,mapKey, value);
    }
    //update a single key-value pair in a hash (UPDATE)
    //may need to fixate String mapKey and update value directly
    public void updateValue(String redisKey,String mapKey,String value) {
        template.opsForHash().put(redisKey, mapKey,value); //hset c01 email fred@gmail.com
    }

    // Get the list of values from hash ( the rawData ) (READALL)
    public List<Object> getAllValuesFromHash(String redisKey) {
    
        return (List<Object>) template.opsForHash().values(redisKey); //hvals c01
        
    }
    // Retrieve a value for a specific key in a hash (READONE)
    public String getValueFromHash(String redisKey, String mapKey) {
        return (String) template.opsForHash().get(redisKey, mapKey); //hget c01 email
        //hgetall c01
    }

    // Delete a specific mapKey from a hash (DELETE)
    public Boolean deleteKeyFromHash(String redisKey, String mapKey) {//hdel c01 email

        Boolean isDeleted = false;
        Long iFound = template.opsForHash().delete(redisKey, mapKey);
        if (iFound>0) {
            isDeleted = true;
        }
        return isDeleted;
        
    }

    // Check if a mapKey exists in a hash
    public Boolean hasKey(String redisKey, String mapKey) { //hexists c01 email
        return template.opsForHash().hasKey(redisKey, mapKey);
    }

    // Get the size of a hash (number of keys)
    public Long getHashSize(String redisKey) { //hlen c01
        return template.opsForHash().size(redisKey);
    }

    // Check if a hash exists
    public Boolean hashExists(String redisKey) {
        return template.hasKey(redisKey);
    }

    // Add multiple key-value pairs to a hash
    public void setMapAll(String redisKey, HashMap<String, String> map) {
        template.opsForHash().putAll(redisKey, map);
    }

    // Retrieve all key-value pairs in a hash
    public Map<Object, Object> getAllFromHash(String redisKey) {
        return template.opsForHash().entries(redisKey);
    }

    
    // Retrieve all keys in a hash
    public Set<Object> getAllKeysFromHash(String redisKey) {
        return template.opsForHash().keys(redisKey); //hkeys c01
    }

    // Retrieve all values in a hash -> already have in a list
    public Set<Object> getAllValues(String redisKey) { //hvals c01
        return (Set<Object>) template.opsForHash().values(redisKey);
    }

    // Increment a numeric value in a hash
    public void incrementHashValue(String redisKey, String mapKey, long delta) {
        template.opsForHash().increment(redisKey, mapKey, delta); //hincrby c01 count 1
    }

    // Increment a floating-point value in a hash
    public void incrementHashValue(String redisKey, String mapKey, double delta) {
        template.opsForHash().increment(redisKey, mapKey, delta);
    }

  
        

    

}   
