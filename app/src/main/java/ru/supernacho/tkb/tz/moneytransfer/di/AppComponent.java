package ru.supernacho.tkb.tz.moneytransfer.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.supernacho.tkb.tz.moneytransfer.di.modules.AppModule;
import ru.supernacho.tkb.tz.moneytransfer.di.modules.PersistenceModule;
import ru.supernacho.tkb.tz.moneytransfer.di.modules.UserRepoModule;
import ru.supernacho.tkb.tz.moneytransfer.di.modules.ViewModelModule;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.view.MainActivity;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.factory.ViewModelFactory;

@Singleton
@Component(modules = {AppModule.class, PersistenceModule.class, UserRepoModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainActivityPresenter presenter);
}
