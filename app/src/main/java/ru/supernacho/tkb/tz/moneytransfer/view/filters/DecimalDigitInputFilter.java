package ru.supernacho.tkb.tz.moneytransfer.view.filters;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalDigitInputFilter implements InputFilter {
    private Pattern pattern;

    public DecimalDigitInputFilter(int digitsAfterZero) {
        pattern = Pattern.compile("[0-9]+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = pattern.matcher(dest);
        if (!matcher.matches()) return "";
        return null;
    }
}
