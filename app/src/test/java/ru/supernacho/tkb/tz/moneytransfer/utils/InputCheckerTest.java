package ru.supernacho.tkb.tz.moneytransfer.utils;

import org.junit.Test;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

import static org.junit.Assert.*;

public class InputCheckerTest {

    @Test
    public void testCheckCard() {
        assertEquals("card number: 4111111111111111", true, InputChecker.checkCard("4111111111111111"));
        assertEquals("card number: 5555555555555599", true, InputChecker.checkCard("5555555555555599"));
        assertEquals("card number: 0000", false, InputChecker.checkCard("0000"));
        assertEquals("card number: null", false, InputChecker.checkCard(null));
        assertEquals("corrupted card number: 4111111111111121", false, InputChecker.checkCard("4111111111111121"));
        assertEquals("corrupted card number: 4411115121411121", false, InputChecker.checkCard("4411115121411121"));
        assertEquals("corrupted card number: 4411115121411", false, InputChecker.checkCard("4411115121411"));
    }

    @Test
    public void testCheckDate() {
        assertEquals("exp date: 01/19", true, InputChecker.checkDate("01/19"));
        assertEquals("exp date: 01/19", true, InputChecker.checkDate("12/19"));
        assertEquals("exp date: 08/18", false, InputChecker.checkDate("08/18"));
        assertEquals("exp date: 08/38", true, InputChecker.checkDate("08/38"));
        assertEquals("exp date: 01/18", false, InputChecker.checkDate("01/18"));
        assertEquals("exp date: 01/18", false, InputChecker.checkDate("12/17"));
        assertEquals("exp date: null", false, InputChecker.checkDate(null));
    }

    @Test
    public void testCheckCVC() {
        assertEquals("cvc 111", true, InputChecker.checkCVC("111"));
        assertEquals("cvc 365", true, InputChecker.checkCVC("365"));
        assertEquals("cvc 36", false, InputChecker.checkCVC("36"));
        assertEquals("cvc 3652", false, InputChecker.checkCVC("3652"));
        assertEquals("cvc ttt", false, InputChecker.checkCVC("ttt"));
        assertEquals("cvc null", false, InputChecker.checkCVC(null));
    }

    @Test
    public void testCheckAmount(){
        assertEquals("amount: 111", true, InputChecker.checkAmount("111"));
        assertEquals("amount: 111.1", true, InputChecker.checkAmount("111.1"));
        assertEquals("amount: 111.11", true, InputChecker.checkAmount("111.11"));
        assertEquals("amount: 111.1111", false, InputChecker.checkAmount("111.1111"));
        assertEquals("amount: -111", false, InputChecker.checkAmount("-111"));
        assertEquals("amount: -111.1", false, InputChecker.checkAmount("-111.1"));
        assertEquals("amount: -111.11", false, InputChecker.checkAmount("-111.11"));
        assertEquals("amount: -111.111", false, InputChecker.checkAmount("-111.111"));
        assertEquals("amount: some", false, InputChecker.checkAmount("some"));
    }

    @Test
    public void testCheckSenderCard(){
        Card sender = new Card("4111111111111111", "01/19","TKB");
        sender.setCvv("000");
        Card wrongSender = new Card("4111111111111113", "01/19","TKB");
        wrongSender.setCvv("000");
        Card beneficiary = new Card("4111111111111111");
        Card wrongBeneficiary = new Card("4111111111111113");
        assertEquals("test sender card 1", true, InputChecker.checkSenderReady(sender));
        assertEquals("test sender card 2", false, InputChecker.checkSenderReady(wrongSender));
        assertEquals("test beneficiary card 1", true, InputChecker.checkBeneficiaryReady(beneficiary));
        assertEquals("test beneficiary card 2", false, InputChecker.checkBeneficiaryReady(wrongBeneficiary));
    }
}