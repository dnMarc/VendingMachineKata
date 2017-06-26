package combinedtests;

import static org.junit.Assert.*;
import org.junit.Test;

import vending.VendingMachine;
import static currency.Coin.*;

public class IntegrationTests {

    
    //BEGIN Accept Coins Functionality Tests
    
    @Test
    public void systemDisplaysInsertCoinWithNoCoinsInserted() {
        VendingMachine vendingMachine = new VendingMachine();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void systemDisplaysCurrentBalanceLessThanOneDollar(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insert(QUARTER, DIME);
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("$0.35", displayStatus);
    }

}
