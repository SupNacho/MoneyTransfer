package ru.supernacho.tkb.tz.moneytransfer.viewmodel.bindingadapters.EditText;

import android.databinding.BindingAdapter;
import android.widget.EditText;

public class EditTextCursorAdapter {
    @BindingAdapter("cursorPosition")
    public static void bindCursorPosition(EditText editText, String string){
        if (string != null){
            editText.setSelection(string.length());
        }
    }
}
