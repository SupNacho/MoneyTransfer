package ru.supernacho.tkb.tz.moneytransfer.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.supernacho.tkb.tz.moneytransfer.App;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App app(){
        return app;
    }
}
