package ru.supernacho.tkb.tz.moneytransfer.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import ru.supernacho.tkb.tz.moneytransfer.model.IUserRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.UserRepository;

@Singleton
@Module
public class UserRepoModule {
    IUserRepository userRepository(){
        return new UserRepository();
    }
}
