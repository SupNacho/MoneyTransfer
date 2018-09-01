package ru.supernacho.tkb.tz.moneytransfer.model;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.User;

public interface IUserRepository {
    void setUser(String token);
    User getCurrentUser();
}
