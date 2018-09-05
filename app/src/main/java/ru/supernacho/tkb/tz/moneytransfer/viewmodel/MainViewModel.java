package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;

public class MainViewModel extends ViewModel {

    public final ObservableField<String> amountField;

    public MainViewModel() {
        this.amountField = new ObservableField<>("0");
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
}
