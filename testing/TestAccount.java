package com.eahlbrecht.robinrecords.testing;

import com.eahlbrecht.robinrecords.account.Account;
import com.eahlbrecht.robinrecords.market.Position;

import java.sql.Date;
import java.time.Instant;

import static java.lang.System.exit;

/**
 * Created by defq0n on 4/4/17.
 */
public class TestAccount {

    public static void main(String[] args){
        //testAddBuyPosition();
        //testBuySellPosition();
        //testIncompletePositions();
        //testMultiplePositions();
        //testAdditionalPositions();
        testAddAdditonalPositions();
    }

    private static void l(String s){
        System.out.println(s);
    }

    private static void assertTrue(boolean bool){
        if(!bool) {
            System.out.println("--FALSE ASSERITION--");
            exit(0);
        }
    }

    public static void testAddAdditonalPositions(){
        Account account = new Account();
                l("Adding one buy position");
        Position pos1 = new Position("TEST",
                1.00,
                5,
                Date.from(Instant.EPOCH),
                Position.ORDER.LIMIT,
                true,
                false);
        account.newPosition(pos1);

                l("Adding one buy position");
        Position pos2 = new Position("TEST",
                1.00,
                4,
                Date.from(Instant.EPOCH),
                Position.ORDER.LIMIT,
                true,
                false);
        account.newPosition(pos2);


    }

//    public static void testAddBuyPosition(){
//        l("-----Starting testAddBuyPosition-----\n\n");
//
//        l("Creating a new account");
//        Account account = new Account();
//        l(account.toString());
//
//        // Check that currentPositions is currently of size 0
//        assertTrue(account.getCurrentPositions().size() == 0);
//        l("Assertion of currentPosition == 0 passed");
//
//        // Test with one buy position;
//        l("Adding one buy position");
//        Position pos1 = new Position("TEST",
//                1.00,
//                5,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//        account.addPosition(pos1);
//        l(account.toString());
//
//        //currentPositions size should now be one
//        assertTrue(account.getCurrentPositions().size() == 1);
//        l("Assertion of currentPosition == 1 passed");
//
//        //Add a second different Position
//        l("Adding a second buy position of different ticker");
//        Position pos2 = new Position("TEST2",
//                1.00,
//                5,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.MARKET,
//                true,
//                false);
//        account.addPosition(pos2);
//        l(account.toString());
//
//        //currentPositions size should now be two
//        assertTrue(account.getCurrentPositions().size() == 2);
//        l("Assertion of currentPosition 2 passed");
//
//        //Add another position of the same ticker, should be added to their additionalPositions and not currentPositions
//        l("Adding a third buy position of the same ticker");
//        Position pos3 = new Position("TEST",
//                1.50,
//                10,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.STOP_LIMIT,
//                true,
//                false);
//        account.addPosition(pos3);
//        l(account.toString());
//
//        //currentPositions size should still be two and pos1.getAdditionalPositions.size() should be 1
//        assertTrue(account.getCurrentPositions().size() == 2);
//        l("Assertion of currentPosition == 2 passed");
//        assertTrue(pos1.getAdditionalPositions().size() == 1);
//        l("Asserition of pos1.getAdditionalPosition.size() == 1 passed");
//
//        l("-----Ending testAddBuyPosition()-----\n\n");
//    }
//
//    public static void testBuySellPosition(){
//        l("-----Testing testBuySellPosition()-----");
//
//        // Create a new empty account
//        Account account = new Account();
//        l("Creating a new account");
//        l(account.toString());
//
//        // Assert that currentPositions.size() == 0 && previousPositions.size() == 0
//        assertTrue(account.getCurrentPositions().size() == 0 && account.getPreviousPositions().size() == 0);
//        l("Assertion of account.getCurrentPositions().size() == 0 && account.getPreviousPositions().size() == 0 passed");
//
//        // Create a new position buying the TEST stock
//        Position posBuy = new Position("TEST",
//                1.00,
//                5,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.MARKET,
//                true,
//                false);
//        account.addPosition(posBuy);
//        l("Adding one buy position of TEST");
//        l(account.toString());
//
//        assertTrue(account.getCurrentPositions().size() == 1);
//        l("account.getCurrentPositions().size() == 1 passed");
//
//        // Create a new position selling the TEST stock
//        Position posSell = new Position("TEST",
//                1.50,
//                5,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.MARKET,
//                false,
//                false);
//        account.addPosition(posSell);
//        l("Adding one sell position of TEST");
//        l(account.toString());
//
//        assertTrue(account.getCurrentPositions().size() == 0 && account.getPreviousPositions().size() == 2);
//        l("account.getCurrentPositions().size() == 0 && account.getPreviousPositions().size() == 2");
//
//        l("-----Ending testBuySellPosition()-----");
//    }
//
//    public static void testIncompletePositions(){
//        l("-----Starting testIncompletePositions-----");
//
//        // Create a new empty account
//        Account account = new Account();
//        l("Creating a new account");
//        l(account.toString());
//
//        // Create a new position of TEST
//        l("Adding one buy position");
//        Position posBuy = new Position("TEST",
//                1.00,
//                5,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//        account.addPosition(posBuy);
//        l(account.toString());
//
//        assertTrue(account.getCurrentPositions().size() == 1);
//
//        l("Adding one sell position");
//        Position posSell1 = new Position("TEST",
//                1.5,
//                3,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                false);
//        account.addPosition(posSell1);
//        l(account.toString());
//
//        assertTrue(account.getCurrentPositions().size() == 1 && account.getPreviousPositions().size() == 1);
//
//        l("Adding a second sell position");
//        Position posSell2 = new Position("TEST",
//                1.5,
//                1,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                false);
//        account.addPosition(posSell2);
//        l(account.toString());
//
//        assertTrue(account.getCurrentPositions().size() == 1 && account.getPreviousPositions().size() == 2);
//
//        l("Adding a third sell position");
//        Position posSell3 = new Position("TEST",
//                1.5,
//                1,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                false);
//        account.addPosition(posSell2);
//        l(account.toString());
//
//        assertTrue(account.getCurrentPositions().size() == 0 && account.getPreviousPositions().size() == 4);
//        l("Assertion of account.getCurrentPositions().size() == 0 && account.getPreviousPositions().size() == 4 passed");
//
//        l("-----Ending testIncompletePositions()-----");
//
//    }
//
//    private static void testMultiplePositions(){
//        l("-----Starting testMultiplePositions-----");
//
//        Account account = new Account();
//
//        l("Adding one buy position TEST");
//        Position posBuy = new Position("TEST",
//                1.00,
//                3,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//        account.addPosition(posBuy);
//        l(account.toString());
//
//        l("Adding a second buy position of TEST");
//        Position posBuy2 = new Position("TEST",
//                1.00,
//                4,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//        account.addPosition(posBuy);
//        l(account.toString());
//
//        l("Add a third buy position of TEST");
//        Position posBuy3 = new Position("TEST",
//                1.00,
//                5,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//        account.addPosition(posBuy);
//        l(account.toString());
//
//        l("Add a pos sell 3");
//        Position posSell1 = new Position("TEST",
//                1.2,
//                2,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                true);
//        account.addPosition(posSell1);
//        l(account.toString());
//
//        l("Add a pos sell 2");
//        Position posSell2 = new Position("TEST",
//                1.2,
//                1,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                true);
//        account.addPosition(posSell2);
//        l(account.toString());
//
//        l("Add a pos sell 6");
//        Position posSell3 = new Position("TEST",
//                1.2,
//                6,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                true);
//        account.addPosition(posSell3);
//        l(account.toString());
//
//        l("Add a pos sell 4");
//        Position posSell4 = new Position("TEST",
//                1.2,
//                3,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                false,
//                true);
//        account.addPosition(posSell4);
//        l(account.toString());
//
//    }
//
//    public static void testAdditionalPositions(){
//        Account account = new Account();
//
//        l("Adding one buy position TEST");
//        Position posBuy = new Position("TEST",
//                1.00,
//                3,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//        account.addPosition(posBuy);
//        l(account.toString());
//
//        l("Adding a second buy position of TEST");
//        Position posBuy2 = new Position("TEST",
//                1.00,
//                4,
//                Date.from(Instant.EPOCH),
//                Position.ORDER.LIMIT,
//                true,
//                false);
//
//        account.getCurrentPositions().get(0).addAdditionalPosition(posBuy2);
//        account.getCurrentPositions().get(0).toString();
//
//        l(account.toString());
//    }
}
