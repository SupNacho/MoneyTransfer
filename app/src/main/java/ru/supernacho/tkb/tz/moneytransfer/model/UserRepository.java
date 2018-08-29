package ru.supernacho.tkb.tz.moneytransfer.model;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.User;

public class UserRepository implements IUserRepository{
    private User user;

    public UserRepository() {
        this.user = new User();
    }

    @Override
    public User setUser(String token) {
        user.setToken(token);
        return user;
    }

    @Override
    public User getCurrentUser() {
        return user;
    }
}
