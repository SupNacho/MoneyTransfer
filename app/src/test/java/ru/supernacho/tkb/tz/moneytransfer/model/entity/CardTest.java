package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    private Card cardOne = new Card("4111111111111111");
    private Card cardTwo = new Card("4111111111111111");
    private Card cardThree = new Card("5555555555555599");

    @Test
    public void testEquals() {
        assertEquals("Compare two equals card", true, cardOne.equals(cardTwo));
        assertNotEquals("Compare two not equals card", true, cardOne.equals(cardThree));
    }

    @Test
    public void testHashCode() {
        assertEquals("Test hashcode two equals card", cardTwo.hashCode(), cardOne.hashCode());
        assertNotEquals("Test hashcode two equals card", cardThree.hashCode(), cardOne.hashCode());
    }
}