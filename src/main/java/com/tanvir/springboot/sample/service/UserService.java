package com.tanvir.springboot.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanvir.springboot.sample.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public User getUserById(String id) {
        String key = "user:" + id;
        String userJson = redisTemplate.opsForValue().get(key);
        if (userJson != null) {
            try {
                return objectMapper.readValue(userJson, User.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace(); // Handle exception properly in your application
            }
        }
        return null;
    }

    public User createUser(User user) {
        String key = "user:" + UUID.randomUUID().toString();
        user.setId(key);
        try {
            String userJson = objectMapper.writeValueAsString(user);
            log.info("Saving user to Redis: " + userJson);
            redisTemplate.opsForValue().set(key, userJson, 1, TimeUnit.HOURS);
            log.info("User saved in Redis with key: " + key);
            return user;
        } catch (JsonProcessingException e) {
            log.error("Error while Creating User  " + e.getLocalizedMessage());
            return User.builder().build();
        }
    }

    public User updateUser(String id, User user) {
        String key = "user:" + id;
        try {
            User existingUser = getUserById(id);
            if (ObjectUtils.isEmpty(existingUser) && ObjectUtils.isEmpty(user)) {
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                String userJson = objectMapper.writeValueAsString(user);
                redisTemplate.opsForValue().set(key, userJson, 1, TimeUnit.HOURS); // Cache user for 1 hour
            }
            return user;
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle exception properly in your application
            return null;
        }
    }

    public boolean deleteUser(String id) {
        String key = "user:" + id;
        return redisTemplate.delete(key);
    }
}
