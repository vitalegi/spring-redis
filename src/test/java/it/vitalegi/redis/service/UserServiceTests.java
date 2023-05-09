package it.vitalegi.redis.service;

import it.vitalegi.redis.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserService userService;

    User user1;
    User user2;

    private Optional<User> getCachedUser(Integer id) {
        return Optional.ofNullable(cacheManager.getCache("userCache")).map(c -> c.get(id, User.class));
    }

    @Test
    void givenAddUserIsCalled_whenGetUser_thenResultShouldBeRetrievedFromTheCache() {
        var user = userService.addUser(user1);
        assertTrue(getCachedUser(1).isPresent());
        assertEquals("sample1", getCachedUser(1).get().getName());
    }

    @Test
    void givenUserIsInCache_whenGetUser_thenResultShouldBeRetrievedFromTheCache() {
        var user = userService.addUser(user1);
        userService.users = new ArrayList<>();
        var search = userService.getUserById(1);
        assertEquals("sample1", search.getName());
        assertEquals("sample1", getCachedUser(1).get().getName());
    }

    @BeforeEach
    void init() {
        user1 = new User();
        user1.setId(1);
        user1.setName("sample1");

        user2 = new User();
        user2.setId(2);
        user2.setName("sample2");
    }
}
