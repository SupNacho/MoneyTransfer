package ru.supernacho.tkb.tz.moneytransfer.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.factory.ViewModelFactory;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel mainViewModel(MainViewModel mainViewModel);

}



