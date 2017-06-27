package vending;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static vending.Product.*;

public class VendingMachineTest {
    
    private static final int SUFFICIENT_VALUE_FOR_ANY_PURCHASE = 100;
    private static final int ZERO_VALUE_SYSTEM_BALANCE         = 0;
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
        vendingMachine.attemptProductPurchase(COLA, SUFFICIENT_VALUE_FOR_ANY_PURCHASE);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, COLA));
    }
    
    @Test
    public void productReturnContainsChipsAfterChipsPurchase(){
        vendingMachine.attemptProductPurchase(CHIPS, SUFFICIENT_VALUE_FOR_ANY_PURCHASE);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, CHIPS));
    }
    
    @Test
    public void productReturnEmptyWithFailedPurchaseAttempt(){
        vendingMachine.attemptProductPurchase(COLA, ZERO_VALUE_SYSTEM_BALANCE);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void productReturnContainsCandyAfterCandyPurchase(){
        vendingMachine.attemptProductPurchase(CANDY, SUFFICIENT_VALUE_FOR_ANY_PURCHASE);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, CANDY));
    }
    
    @Test
    public void returnedProductCanOnlyBeCollectedOnce(){
        vendingMachine.attemptProductPurchase(CANDY, SUFFICIENT_VALUE_FOR_ANY_PURCHASE);
        vendingMachine.checkProductReturn();
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void displayPriceWhenColaPurchaseAttemptedWithInsufficientValue(){
    	vendingMachine.attemptProductPurchase(COLA, ZERO_VALUE_SYSTEM_BALANCE);
    	String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE);
    	assertEquals("PRICE $1.00", displayStatus);
    }
    
    
    private boolean productReturnedIsAsExpected(List<Product> dispensedProduct, Product expectedProductType){
        if (dispensedProduct.size() == 1){
            if (dispensedProduct.get(0) == expectedProductType){
                return true;
            }
        }
        return false;
    }

}
