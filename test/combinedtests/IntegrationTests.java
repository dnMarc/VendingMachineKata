package combinedtests;

import static org.junit.Assert.*;
import org.junit.Test;

import vending.VendingMachine;

public class IntegrationTests {

    
    //BEGIN Accept Coins Functionality Tests
    
    @Test
    public void systemDisplaysInsertCoinWithNoCoinsInserted() {
        VendingMachine vendingMachine = new VendingMachine();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("INSERT COIN", displayStatus);
    }

}
