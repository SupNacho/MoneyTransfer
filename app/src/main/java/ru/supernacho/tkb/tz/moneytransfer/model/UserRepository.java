package ru.supernacho.tkb.tz.moneytransfer.model;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.User;

public class UserRepository implements IUserRepository{
    private User user;

    public UserRepository() {
        this.user = new User();
    }

    @Override
    public void setUser(String token) {
        user.setToken(token);
    }

    @Override
    public User getCurrentUser() {
        return user;
    }
}
