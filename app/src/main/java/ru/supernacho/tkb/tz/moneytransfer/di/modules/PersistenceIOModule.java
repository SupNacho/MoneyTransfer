package ru.supernacho.tkb.tz.moneytransfer.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceIO;
import ru.supernacho.tkb.tz.moneytransfer.model.PersistenceIO;

@Singleton
@Module(includes = AppModule.class)
public class PersistenceIOModule {
    @Provides
    IPersistenceIO persistenceIO(App app){
        return new PersistenceIO(app);
    }
}
