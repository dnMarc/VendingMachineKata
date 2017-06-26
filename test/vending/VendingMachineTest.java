package vending;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VendingMachineTest {
    
    private VendingMachine vendingMachine;
    
    @Before
    public void setup(){
        vendingMachine = new VendingMachine();
    }

    //BEGIN Accept Coins Functionality Tests
    
    @Test
    public void displayInsertCoinWithZeroSystemValue() {
        String displayStatus = vendingMachine.createSystemDisplay(0);
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceLessThanTenCents(){
        String displayStatus = vendingMachine.createSystemDisplay(5);
        assertEquals("$0.05", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceLessThanOneDollar(){
        String displayStatus = vendingMachine.createSystemDisplay(15);
        assertEquals("$0.15", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceGreaterThanNinetyNineCents(){
        String displayStatus = vendingMachine.createSystemDisplay(127);
        assertEquals("$1.27", displayStatus);
    }
    
    //END Accept Coins Functionality Tests
    
    //BEGIN Select Product Functionality Tests
   
    @Test
    public void productReturnEmptyWithNoPurchaseAttempt(){
        List<Product> returnedProducts = vendingMachine.checkProductReturn();
        assertTrue(returnedProducts.isEmpty());
    }

}
