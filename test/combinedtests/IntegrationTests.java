package combinedtests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import vending.VendingMachine;
import currency.Coin;
import static currency.Coin.*;
import static currency.CoinControllerTest.*;

public class IntegrationTests {
    
    private VendingMachine vendingMachine;
    
    @Before
    public void setup(){
        vendingMachine = new VendingMachine();
    }

    //BEGIN Accept Coins Functionality Tests
    
    @Test
    public void systemDisplaysInsertCoinWithNoCoinsInserted() {
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void systemDisplaysCurrentBalanceLessThanOneDollar(){
        vendingMachine.insert(QUARTER, DIME);
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("$0.35", displayStatus);
    }
    
    @Test
    public void systemDisplaysCurrentBalanceGreaterThanNinetyNineCents(){
        vendingMachine.insert(QUARTER, QUARTER, DIME, NICKEL, QUARTER, QUARTER, NICKEL);
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("$1.20", displayStatus);
    }
    
    @Test
    public void coinReturnEmptyWhenNoCoinsInserted(){
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void coinReturnEmptyWhenOnlyValidCoinsInserted(){
        vendingMachine.insert(QUARTER, DIME, NICKEL, DIME, NICKEL);
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void coinReturnContainsPennyWhenPennyInserted(){
        vendingMachine.insert(QUARTER, PENNY, NICKEL, DIME);
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinReturnedIsAsExpected(returnedCoins, PENNY));
    }
    
    //END Accept Coins Functionality Tests
    
    //BEGIN Select Product Functionality Tests
    
    @Test
    public void systemDisplaysPriceWithFailedColaPurchaseAttempt(){
        vendingMachine.attemptColaPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("PRICE $1.00", displayStatus);
    }

}
