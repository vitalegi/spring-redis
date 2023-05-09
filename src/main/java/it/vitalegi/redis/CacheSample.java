package it.vitalegi.redis;

import it.vitalegi.redis.model.User;
import it.vitalegi.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheSample implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        run();
    }

    @Scheduled(fixedDelay = 1000)
    public void run() {
        User user = new User();
        user.setId(1);
        user.setName("aaa");
        userService.addUser(user);
        log.info("id 1");
        log.info("User: {}", userService.getUserById(1));
        log.info("User: {}", userService.getUserById(1));
        log.info("User: {}", userService.getUserById(1));

        user = new User();
        user.setId(2);
        user.setName("bbb");
        userService.addUser(user);
        log.info("id 2");
        log.info("User: {}", userService.getUserById(2));
        log.info("User: {}", userService.getUserById(2));
        log.info("User: {}", userService.getUserById(2));

        log.info("id 3 (absent)");
        log.info("User: {}", userService.getUserById(3));
    }
}
