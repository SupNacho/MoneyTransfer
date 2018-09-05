package ru.supernacho.tkb.tz.moneytransfer.viewmodel.bindingadapters.EditText;

import android.databinding.BindingAdapter;
import android.widget.EditText;

import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;


public class EditTextErrorAdapter {
    @BindingAdapter("setError")
    public static void setErrorToField(EditText editText, String string) {
        if (string != null) {
            switch (editText.getId()) {
                case R.id.et_amount_input:
                    setError(editText, InputChecker.checkAmount(string), R.string.amount_error);
                    break;
                case R.id.et_cvv:
                    setError(editText, InputChecker.checkCVC(string), R.string.cvv_to_short);
                    break;
                case R.id.et_cached_cvv:
                    setError(editText, InputChecker.checkCVC(string), R.string.cvv_to_short);
                    break;
                case R.id.et_card_number:
                    setError(editText, InputChecker.checkCard(string), R.string.card_number_error);
                    break;
                case R.id.et_exp_date:
                    setError(editText, InputChecker.checkDate(string), R.string.card_exp_date_error);
                    break;
            }
        }
    }

    private static void setError(EditText editText, boolean b, int errorResource) {
        if (!b)
            editText.setError(App.getInstance().getApplicationContext().getResources().getString(errorResource));
    }
}
