package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardsCollectionTest {

    private CardsCollection collectionOne;
    private List<Card> listOne;
    private List<Card> listTwo;
    private Card cardOne = new Card("4111111111111111");
    private Card cardTwo = new Card("4111111111111111");
    private Card cardThree = new Card("5555555555555599");

    @Before
    public void setUp() throws Exception {
        collectionOne = new CardsCollection();
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetSenderCards() {
        collectionOne.addToSenders(cardOne);
        listOne = collectionOne.getSenderCards();
        listTwo.add(cardThree);
        assertEquals("expected List<Card>", listOne, collectionOne.getSenderCards());
        assertNotEquals("expected List<Card>", listTwo, collectionOne.getSenderCards());
    }

    @Test
    public void testAddToSenders() {
        collectionOne.addToSenders(cardOne);
        assertEquals("Added card one", cardOne, collectionOne.getSenderCards().get(0));
    }

    @Test
    public void testAddAllToSenders() {
        listOne.add(cardOne);
        listOne.add(cardThree);
        collectionOne.addAllToSenders(listOne);
        assertEquals("Returned list equals to list one", listOne, collectionOne.getSenderCards());
    }

    @Test
    public void testAddAllToBeneficiary() {
        listOne.add(cardOne);
        listOne.add(cardThree);
        collectionOne.addAllToBeneficiary(listOne);
        assertEquals("Returned list equals to list one", listOne, collectionOne.getBeneficiaryCards());
    }

    @Test
    public void testAddToBeneficiary() {
        collectionOne.addToBeneficiary(cardOne);
        assertEquals("Added card one", cardOne, collectionOne.getBeneficiaryCards().get(0));
    }

    @Test
    public void testGetBeneficiaryCards() {
        collectionOne.addToBeneficiary(cardOne);
        listOne = collectionOne.getBeneficiaryCards();
        listTwo.add(cardThree);
        assertEquals("expected List<Card>", listOne, collectionOne.getBeneficiaryCards());
        assertNotEquals("expected List<Card>", listTwo, collectionOne.getBeneficiaryCards());
    }
}