package ru.supernacho.tkb.tz.moneytransfer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InputChecker {
    public static boolean checkCard(String cardNumber) {
        if (cardNumber != null && cardNumber.length() > 12) {
            char[] numbers = cardNumber.toCharArray();
            int[] digits = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                digits[i] = numbers[i] - 48;
            }
            int sum = 0;
            int length = digits.length;
            for (int i = 0; i < length; i++) {

                // get digits in reverse order
                int digit = digits[length - i - 1];

                // every 2nd number multiply by 2
                if (i % 2 == 1) {
                    digit *= 2;
                }
                sum += digit > 9 ? digit - 9 : digit;
            }
            return sum % 10 == 0;
        }
        return false;
    }

    public static boolean checkDate(String date) {
        if (date != null) {
            SimpleDateFormat parser = new SimpleDateFormat("MM/yy", Locale.US);
            try {
                Date cardDate = parser.parse(date);
                Calendar cal = Calendar.getInstance();
                String current = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                Date currentDate = parser.parse(current);
                return cardDate.getTime() >= currentDate.getTime();
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkCVC(String cvc) {
        return cvc != null && cvc.toCharArray().length < 4;
    }
}
