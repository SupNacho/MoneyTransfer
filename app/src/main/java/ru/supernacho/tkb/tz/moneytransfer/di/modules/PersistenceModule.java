package ru.supernacho.tkb.tz.moneytransfer.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceIO;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.PersistenceRepository;

@Singleton
@Module(includes = PersistenceIOModule.class)
public class PersistenceModule {
    @Provides
    IPersistenceRepository persistenceRepository(IPersistenceIO persistenceIO){
        return new PersistenceRepository(persistenceIO);
    }
}
