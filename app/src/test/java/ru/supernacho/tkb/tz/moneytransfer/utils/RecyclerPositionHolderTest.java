package ru.supernacho.tkb.tz.moneytransfer.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecyclerPositionHolderTest {

    @Test
    public void getActualPosition() {
        RecyclerPositionHolder positionHolder = new RecyclerPositionHolder();
        //First ViewHolder attached
        positionHolder.setPosition(0);
        assertEquals("First VH attached", 0 , positionHolder.getActualPosition());
        assertNotEquals("First VH attached", 1 , positionHolder.getActualPosition());
        //Success swipe to next recycler element
        positionHolder.setPosition(1);
        positionHolder.doCorrection(0);
        assertEquals("Swipe to next element", 1, positionHolder.getActualPosition());
        assertNotEquals("Swipe to next element", 0, positionHolder.getActualPosition());
        //Failed/Canceled swipe to next element
        positionHolder.setPosition(2);
        positionHolder.doCorrection(2);
        assertEquals("Failed/canceled swipe to next element", 1, positionHolder.getActualPosition());
        assertNotEquals("Failed/canceled swipe to next element", 2, positionHolder.getActualPosition());
    }
}