package ru.supernacho.tkb.tz.moneytransfer.viewmodel.bindingadapters.EditText;

import android.databinding.BindingAdapter;
import android.widget.EditText;

import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;


public class EditTextErrorAdapter {
    @BindingAdapter({"setError", "setViewModel", "isNewCard"})
    public static void setErrorToField(EditText editText, String string, MainViewModel mainViewModel, boolean isNewCard) {
        if (string != null) {
            switch (editText.getId()) {
                case R.id.et_amount_input:
                    setError(editText, InputChecker.checkAmount(string), R.string.amount_error, mainViewModel, isNewCard);
                    break;
                case R.id.et_cvv:
                    setError(editText, InputChecker.checkCVC(string), R.string.cvv_to_short, mainViewModel, isNewCard);
                    break;
                case R.id.et_cached_cvv:
                    setError(editText, InputChecker.checkCVC(string), R.string.cvv_to_short, mainViewModel, isNewCard);
                    break;
                case R.id.et_card_number:
                    setError(editText, InputChecker.checkCard(string), R.string.card_number_error, mainViewModel, isNewCard);
                    break;
                case R.id.et_exp_date:
                    setError(editText, InputChecker.checkDate(string), R.string.card_exp_date_error, mainViewModel, isNewCard);
                    break;
            }
        }
    }

    private static void setError(EditText editText, boolean b, int errorResource,
                                 MainViewModel mainViewModel, boolean isNewCard) {
        if (!b)
            editText.setError(App.getInstance().getApplicationContext().getResources().getString(errorResource));
        else {
            switch (editText.getId()) {
                case R.id.et_amount_input:
                    mainViewModel.amountAccepted.set(true);
                    break;
                case R.id.et_cvv:
                    mainViewModel.senderCvvAccepted.set(true);
                    break;
                case R.id.et_cached_cvv:
                    mainViewModel.senderCvvAccepted.set(true);
                    if (!isNewCard){
                        mainViewModel.senderCardAccepted.set(true);
                        mainViewModel.senderDateAccepted.set(true);
                    }
                    break;
                case R.id.et_card_number:
                    mainViewModel.senderCardAccepted.set(true);
                    break;
                case R.id.et_exp_date:
                    mainViewModel.senderDateAccepted.set(true);
                    break;
            }
        }
    }
}
