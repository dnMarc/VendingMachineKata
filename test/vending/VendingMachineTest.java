package vending;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static vending.Product.*;
import static vending.VendingMachine.*;

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
        String displayStatus = vendingMachine.createSystemDisplay(0, false);
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceLessThanTenCents(){
        String displayStatus = vendingMachine.createSystemDisplay(5, false);
        assertEquals("$0.05", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceLessThanOneDollar(){
        String displayStatus = vendingMachine.createSystemDisplay(15, false);
        assertEquals("$0.15", displayStatus);
    }
    
    @Test
    public void displaySystemBalanceGreaterThanNinetyNineCents(){
        String displayStatus = vendingMachine.createSystemDisplay(127, false);
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
        vendingMachine.attemptProductPurchase(COLA, SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, COLA));
    }
    
    @Test
    public void productReturnContainsChipsAfterChipsPurchase(){
        vendingMachine.attemptProductPurchase(CHIPS, SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, CHIPS));
    }
    
    @Test
    public void productReturnEmptyWithFailedPurchaseAttempt(){
        vendingMachine.attemptProductPurchase(COLA, ZERO_VALUE_SYSTEM_BALANCE, false);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void productReturnContainsCandyAfterCandyPurchase(){
        vendingMachine.attemptProductPurchase(CANDY, SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, CANDY));
    }
    
    @Test
    public void returnedProductCanOnlyBeCollectedOnce(){
        vendingMachine.attemptProductPurchase(CANDY, SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        vendingMachine.checkProductReturn();
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void displayPriceWhenColaPurchaseAttemptedWithInsufficientValue(){
    	vendingMachine.attemptProductPurchase(COLA, ZERO_VALUE_SYSTEM_BALANCE, false);
    	String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
    	assertEquals("PRICE $1.00", displayStatus);
    }
    
    @Test
    public void displayPriceWhenChipsPurchaseAttemptedWithInsufficientValue(){
        vendingMachine.attemptProductPurchase(CHIPS, ZERO_VALUE_SYSTEM_BALANCE, false);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("PRICE $0.50", displayStatus);
    }
    
    @Test
    public void displayPriceWhenCandyPurchaseAttemptedWithInsufficientValue(){
        vendingMachine.attemptProductPurchase(CANDY, ZERO_VALUE_SYSTEM_BALANCE, false);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("PRICE $0.65", displayStatus);
    }
    
    @Test
    public void displayInsertCoinAfterPriceWithEmptySystemBalance(){
        vendingMachine.attemptProductPurchase(CANDY, ZERO_VALUE_SYSTEM_BALANCE, false);
        vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void displayBalanceAfterPriceWithInsufficientSystemBalance(){
        vendingMachine.attemptProductPurchase(CANDY, CANDY_COST_IN_CENTS - 10, false);
        vendingMachine.createSystemDisplay(CANDY_COST_IN_CENTS - 10, false);
        String displayStatus = vendingMachine.createSystemDisplay(CANDY_COST_IN_CENTS - 10, false);
        assertEquals("$0.55", displayStatus);
    }
    
    @Test
    public void displayThankYouAfterSuccessfulProductPurchase(){
        vendingMachine.attemptProductPurchase(COLA, SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("THANK YOU", displayStatus);
    }
    
    //END Select Product Functionality Tests
    
    //BEGIN Make Change Functionality Tests
    
    @Test
    public void exactProductCostUsedForPurchaseEqualsZeroExcessValue(){
        int excessValueInCents = vendingMachine.calculateExcessValue(CHIPS, CHIPS_COST_IN_CENTS);
        assertEquals(0, excessValueInCents);
    }
    
    @Test
    public void excessValueUsedForPurchaseEqualsPositiveExcessValue(){
        int excessValueInCents = vendingMachine.calculateExcessValue(CHIPS, CHIPS_COST_IN_CENTS + 10);
        assertEquals(10, excessValueInCents);
    }
    
    //END Make Change Functionality Tests
    
    //BEGIN Sold Out Functionality Tests
    
    @Test
    public void displaySoldOutWhenChipsPurchaseAttemptedWithChipsSoldOut(){
        purchaseProductNumTimes(CHIPS, NUM_UNITS_INITIALLY_STOCKED + 1);
        String displayStatus = vendingMachine.createSystemDisplay(SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        assertEquals("SOLD OUT", displayStatus);
    }
    
    @Test
    public void displayThankYouWhenCandyPurchaseAttemptedWithChipsSoldOut(){
        purchaseProductNumTimes(CHIPS, NUM_UNITS_INITIALLY_STOCKED);
        purchaseProductNumTimes(CANDY, 1);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("THANK YOU", displayStatus);
    }
    
    @Test
    public void displaySoldOutWhenCandyPurchaseAttemptedWithCandySoldOut(){
        purchaseProductNumTimes(CANDY, NUM_UNITS_INITIALLY_STOCKED + 1);
        String displayStatus = vendingMachine.createSystemDisplay(SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        assertEquals("SOLD OUT", displayStatus);
    }
    
    @Test
    public void displaySoldOutWhenColaPurchaseAttemptedWithColaSoldOut(){
        purchaseProductNumTimes(COLA, NUM_UNITS_INITIALLY_STOCKED + 1);
        String displayStatus = vendingMachine.createSystemDisplay(SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        assertEquals("SOLD OUT", displayStatus);
    }
    
    @Test
    public void displaySoldOutWhenSoldOutProductPurchaseAttemptedWithInsufficientValue(){
        purchaseProductNumTimes(CHIPS, NUM_UNITS_INITIALLY_STOCKED);
        vendingMachine.attemptProductPurchase(CHIPS, ZERO_VALUE_SYSTEM_BALANCE, false);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("SOLD OUT", displayStatus);
    }
    
    @Test
    public void displayInsertCoinAfterSoldOutWithZeroSystemBalance(){
        purchaseProductNumTimes(CANDY, NUM_UNITS_INITIALLY_STOCKED);
        vendingMachine.attemptProductPurchase(CANDY, ZERO_VALUE_SYSTEM_BALANCE, false);
        vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, false);
        assertEquals("INSERT COIN", displayStatus);
    }
    
    @Test
    public void displayBalanceAfterSoldOutWithPositiveSystemBalance(){
        purchaseProductNumTimes(COLA, NUM_UNITS_INITIALLY_STOCKED + 1);
        vendingMachine.createSystemDisplay(SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        String displayStatus = vendingMachine.createSystemDisplay(SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        assertEquals("$1.00", displayStatus);
    }
    
    @Test
    public void colaNotDispensedWhenSoldOut(){
        purchaseProductNumTimes(COLA, NUM_UNITS_INITIALLY_STOCKED);
        vendingMachine.checkProductReturn();
        purchaseProductNumTimes(COLA, 1);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void chipsNotDispensedWhenSoldOut(){
        purchaseProductNumTimes(CHIPS, NUM_UNITS_INITIALLY_STOCKED);
        vendingMachine.checkProductReturn();
        purchaseProductNumTimes(CHIPS, 1);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void candyNotDispensedWhenSoldOut(){
        purchaseProductNumTimes(CANDY, NUM_UNITS_INITIALLY_STOCKED);
        vendingMachine.checkProductReturn();
        purchaseProductNumTimes(CANDY, 1);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void candyDispensedWhenChipsSoldOut(){
        purchaseProductNumTimes(CHIPS, NUM_UNITS_INITIALLY_STOCKED);
        vendingMachine.checkProductReturn();
        purchaseProductNumTimes(CANDY, 1);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, CANDY));
    }
  
    //END Sold Out Functionality Tests
    
    //BEGIN Exact Change Only Functionality Tests
    
    @Test
    public void displayExactChangeOnlyWithZeroBalanceInExactChangeOnlyState(){
        String displayStatus = vendingMachine.createSystemDisplay(ZERO_VALUE_SYSTEM_BALANCE, true);
        assertEquals("EXACT CHANGE ONLY", displayStatus);
    }
    
    @Test
    public void displayExactChangeOnlyWhenExcessValueUsedForPurchaseInExactChangeOnlyState(){
        vendingMachine.attemptProductPurchase(CHIPS, CHIPS_COST_IN_CENTS + 5, true);
        String displayStatus = vendingMachine.createSystemDisplay(CHIPS_COST_IN_CENTS + 5, true);
        assertEquals("EXACT CHANGE ONLY", displayStatus);
    }
    
    @Test
    public void exactChangeOnlyPurchaseFailsWithSurplusValue(){
        vendingMachine.attemptProductPurchase(CANDY, CANDY_COST_IN_CENTS + 10, true);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(dispensedProducts.isEmpty());
    }
    
    @Test
    public void exactChangeOnlyPurchaseSucceedsWithExactValue(){
        vendingMachine.attemptProductPurchase(CANDY, CANDY_COST_IN_CENTS, true);
        List<Product> dispensedProducts = vendingMachine.checkProductReturn();
        assertTrue(productReturnedIsAsExpected(dispensedProducts, CANDY));
    }
    
    //END Exact Change Only Functionality Tests
    
    
    
    private void purchaseProductNumTimes(Product productToPurchase, int numTimesToPurchase){
        for (int i = 0; i < numTimesToPurchase; i++){
            vendingMachine.attemptProductPurchase(productToPurchase, SUFFICIENT_VALUE_FOR_ANY_PURCHASE, false);
        }
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
