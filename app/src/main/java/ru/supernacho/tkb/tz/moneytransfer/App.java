package ru.supernacho.tkb.tz.moneytransfer;

import android.app.Application;
import android.util.Log;

import io.reactivex.plugins.RxJavaPlugins;
import ru.supernacho.tkb.tz.moneytransfer.di.AppComponent;
import ru.supernacho.tkb.tz.moneytransfer.di.DaggerAppComponent;
import ru.supernacho.tkb.tz.moneytransfer.di.modules.AppModule;

public class App extends Application {
    private static App instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxJavaPlugins.setErrorHandler(e -> Log.d("Error %s", e.getMessage()));
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
