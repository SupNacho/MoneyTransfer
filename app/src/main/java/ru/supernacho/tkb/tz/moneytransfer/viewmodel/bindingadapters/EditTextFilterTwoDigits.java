package ru.supernacho.tkb.tz.moneytransfer.viewmodel.bindingadapters;

import android.databinding.BindingAdapter;
import android.text.InputFilter;
import android.widget.EditText;

import ru.supernacho.tkb.tz.moneytransfer.AppConstants;
import ru.supernacho.tkb.tz.moneytransfer.view.filters.DecimalDigitInputFilter;

public class EditTextFilterTwoDigits {
    @BindingAdapter("limitDigitAfterDot")
    public static void setFilters(EditText editText, String src){
        editText.setFilters(new InputFilter[]{new DecimalDigitInputFilter(AppConstants.DIGITS_AFTER_ZERO)});
    }
}
