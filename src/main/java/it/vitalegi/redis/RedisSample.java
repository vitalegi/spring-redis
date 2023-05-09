package it.vitalegi.redis;

import it.vitalegi.redis.model.User;
import it.vitalegi.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisSample implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("aaa");
        userService.addUser(user);
        log.info("User: {}", userService.getUserById(1));
        log.info("User: {}", userService.getUserById(1));
        log.info("User: {}", userService.getUserById(1));
    }
}
