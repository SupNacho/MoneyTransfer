package ru.supernacho.tkb.tz.moneytransfer;

public interface AppConstants {
    int DIGITS_AFTER_ZERO = 2;
    int CVC_LENGTH = 3;
    int CARD_NUMBER_LENGTH = 12;
    String CARD_NUMBER_MASK = "____ ____ ____ ____";
    String EXP_DATE_MASK = "__" + "/" + "__";
}
