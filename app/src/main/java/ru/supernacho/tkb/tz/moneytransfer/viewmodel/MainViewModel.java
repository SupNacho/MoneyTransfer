package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;

import javax.inject.Inject;

import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.PersistenceIO;
import ru.supernacho.tkb.tz.moneytransfer.model.PersistenceRepository;

public class MainViewModel extends ViewModel {

    public final ObservableField<Boolean> amountAccepted;
    public final ObservableField<String> amountField;
    private IPersistenceRepository repository;

    @Inject
    public MainViewModel(IPersistenceRepository repository) {
        this.repository = repository;
        this.amountAccepted = new ObservableField<>(false);
        this.amountField = repository.getAmountField();
        amountField.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                String str = amountField.get();
                if ( str != null && str.length() > 1){
                    if (str.charAt(0) == '0' && str.charAt(1) != '.'){
                        str = "" + str.charAt(1);
                        amountField.set(str);
                    }
                }
            }
        });
    }

    public void click(){
        repository.click();
    }
}
