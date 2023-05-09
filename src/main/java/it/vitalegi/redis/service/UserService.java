package it.vitalegi.redis.service;

import it.vitalegi.redis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    @Cacheable(value = "userCache")
    public User getUserById(int id) {
        log.info("Search user, id {}", id);
        return users.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(RuntimeException::new);
    }
}
