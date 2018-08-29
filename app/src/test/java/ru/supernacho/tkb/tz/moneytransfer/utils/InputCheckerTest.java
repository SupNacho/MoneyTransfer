package ru.supernacho.tkb.tz.moneytransfer.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputCheckerTest {

    @Test
    public void testCheckCard() {
        assertEquals("card number: 4111111111111111", true, InputChecker.checkCard("4111111111111111"));
        assertEquals("card number: 5555555555555599", true, InputChecker.checkCard("5555555555555599"));
        assertEquals("corrupted card number: 4111111111111121", false, InputChecker.checkCard("4111111111111121"));
        assertEquals("corrupted card number: 4411115121411121", false, InputChecker.checkCard("4411115121411121"));
        assertEquals("corrupted card number: 4411115121411", false, InputChecker.checkCard("4411115121411"));
    }

    @Test
    public void testCheckDate() {
        assertEquals("exp date: 01/19", true, InputChecker.checkDate("01/19"));
        assertEquals("exp date: 01/19", true, InputChecker.checkDate("12/19"));
        assertEquals("exp date: 08/18", true, InputChecker.checkDate("08/18"));
        assertEquals("exp date: 01/18", false, InputChecker.checkDate("01/18"));
        assertEquals("exp date: 01/18", false, InputChecker.checkDate("12/17"));
    }

    @Test
    public void testCheckCVC() {
        assertEquals("cvc 111", true, InputChecker.checkCVC("111"));
        assertEquals("cvc 365", true, InputChecker.checkCVC("365"));
        assertEquals("cvc 36", true, InputChecker.checkCVC("36"));
        assertEquals("cvc 3652", false, InputChecker.checkCVC("3652"));
    }
}