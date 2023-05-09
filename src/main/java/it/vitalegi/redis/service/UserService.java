package it.vitalegi.redis.service;

import it.vitalegi.redis.model.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ToString
public class UserService {

    List<User> users = new ArrayList<>();

    @CachePut(value = "userCache", key = "#result.id")
    public User addUser(User user) {
        log.info("AddUser {}", user);
        users.add(user);
        return user;
    }

    @Cacheable("userCache")
    public User getUserById(Integer id) {
        log.info("Search user, id {}", id);
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

}
