package combinedtests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import vending.VendingMachine;
import static currency.Coin.*;

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

}
