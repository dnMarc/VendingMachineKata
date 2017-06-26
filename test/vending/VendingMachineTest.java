package vending;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static vending.Product.*;

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
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void productReturnContainsColaAfterColaPurchase(){
        vendingMachine.attemptProductPurchase(COLA, 100);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertEquals(1, dispensedProducts.size());
        assertEquals(COLA, dispensedProducts.get(0));
    }

}
