package ru.supernacho.tkb.tz.moneytransfer.model;

import org.junit.Test;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.User;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private IUserRepository userRepository = new UserRepository();

    @Test
    public void testGetCurrentUser() {
        userRepository.setUser("123");
        User controlUser = new User();
        controlUser.setToken("123");
        assertEquals("Get user with token 123", controlUser, userRepository.getCurrentUser());
        assertNotEquals("Wrong user", new User(), userRepository.getCurrentUser());
    }
}