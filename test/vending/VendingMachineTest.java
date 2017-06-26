package vending;

import static org.junit.Assert.*;
import org.junit.Test;

public class VendingMachineTest {

    @Test
    public void displayInsertCoinWithZeroSystemValue() {
        VendingMachine vendingMachine = new VendingMachine();
        String displayStatus = vendingMachine.createSystemDisplay(0);
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceLessThanTenCents(){
        VendingMachine vendingMachine = new VendingMachine();
        String displayStatus = vendingMachine.createSystemDisplay(5);
        assertEquals("$0.05", displayStatus);
    }

}
