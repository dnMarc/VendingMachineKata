package combinedtests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import vending.VendingMachine;
import currency.Coin;
import vending.Product;
import static currency.Coin.*;
import static currency.CoinController.*;
import static currency.CoinControllerTest.*;
import static vending.VendingMachine.*;
import static vending.Product.*;

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
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, PENNY));
    }
    
    //END Accept Coins Functionality Tests
    
    //BEGIN Select Product Functionality Tests
    
    @Test
    public void systemDisplaysPriceWithFailedColaPurchaseAttempt(){
        vendingMachine.attemptColaPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("PRICE $1.00", displayStatus);
    }
    
    @Test
    public void systemDisplaysPriceWithFailedChipsPurchaseAttempt(){
        vendingMachine.attemptChipsPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("PRICE $0.50", displayStatus);
    }
    
    @Test
    public void systemDisplaysPriceWithFailedCandyPurchaseAttempt(){
        vendingMachine.attemptCandyPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("PRICE $0.65", displayStatus);
    }
    
    @Test
    public void systemDisplaysThankYouWithSuccessfulColaPurchase(){
        vendingMachine.insert(QUARTER, QUARTER, QUARTER, QUARTER);
        vendingMachine.attemptColaPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("THANK YOU", displayStatus);
    }
    
    @Test
    public void systemDisplaysThankYouWithSuccessfulChipsPurchase(){
        vendingMachine.insert(QUARTER, QUARTER);
        vendingMachine.attemptChipsPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("THANK YOU", displayStatus);
    }
    
    @Test
    public void systemDisplaysThankYouWithSuccessfulCandyPurchase(){
        vendingMachine.insert(QUARTER, QUARTER, QUARTER);
        vendingMachine.attemptCandyPurchase();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("THANK YOU", displayStatus);
    }
    
    @Test
    public void systemDisplaysInsertCoinAfterThankYou(){
        vendingMachine.insert(QUARTER, QUARTER, QUARTER);
        vendingMachine.attemptCandyPurchase();
        vendingMachine.checkDisplay();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("INSERT COIN", displayStatus);
    }
    
    //END Select Product Functionality Tests
    
    //BEGIN Make Change Functionality Tests
    
    @Test
    public void coinReturnEmptyAfterPurchaseMadeWithExactChange(){
        vendingMachine.insert(QUARTER, QUARTER);
        vendingMachine.attemptChipsPurchase();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void coinReturnContainsOneNickelAfterPurchaseMadeWithExcessFiveCents(){
        vendingMachine.insert(QUARTER, QUARTER, DIME, DIME);
        vendingMachine.attemptCandyPurchase();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, NICKEL));
    }
    
    @Test
    public void coinReturnContainsOneDimeAfterPurchaseMadeWithExcessTenCents(){
        vendingMachine.insert(QUARTER, QUARTER, QUARTER);
        vendingMachine.attemptCandyPurchase();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, DIME));
    }
    
    @Test
    public void coinReturnContainsOneQuarterAfterPurchaseMadeWithExcessTwentyFiveCents(){
        vendingMachine.insert(QUARTER, QUARTER, QUARTER);
        vendingMachine.attemptChipsPurchase();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, QUARTER));
    }
    
    @Test
    public void coinReturnContainsMixValidCoinsAndPenniesAfterPurchase(){
        vendingMachine.insert(QUARTER, PENNY, QUARTER, DIME, NICKEL);
        vendingMachine.attemptChipsPurchase();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, NICKEL, PENNY, DIME));
    }
    
    @Test
    public void coinsCanOnlyBeCollectedOnceFromCoinReturn(){
        vendingMachine.insert(QUARTER, PENNY, QUARTER, DIME, NICKEL);
        vendingMachine.attemptChipsPurchase();
        vendingMachine.checkCoinReturn();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(returnedCoins.isEmpty());
    }
    
    //END Make Change Functionality Tests
    
    //BEGIN Return Coins Functionality Tests
    
    @Test
    public void coinReturnEmptyAfterNoCoinsInsertedAndManualCoinReturnAttempted(){
        vendingMachine.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void coinReturnContainsAllInsertedCoinsAfterManualCoinReturnAttempted(){
        vendingMachine.insert(QUARTER, DIME, QUARTER, NICKEL);
        vendingMachine.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, QUARTER, QUARTER, DIME, NICKEL));
    }
    
    @Test
    public void systemDisplaysInsertCoinAfterManuallyReturningInsertedCoins(){
        vendingMachine.insert(QUARTER);
        vendingMachine.manuallyReturnAllInsertedCoins();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("INSERT COIN", displayStatus);
    }
    
    //END Return Coins Functionality Tests
    
    //BEGIN Sold Out Functionality Tests
    
    @Test 
    public void systemBalanceUnchangedByFailedPurchaseAttemptOfSoldOutItem(){
        for (int i = 0; i < NUM_UNITS_INITIALLY_STOCKED + 1; i++){
            vendingMachine.insert(QUARTER, QUARTER);
            vendingMachine.attemptChipsPurchase();
        }
        vendingMachine.checkDisplay();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("$0.50", displayStatus);
    }
    
    //END Sold Out Functionality Tests
    
    //BEGIN Exact Change Only Functionality Tests
    
    @Test
    public void systemDisplaysExactChangeOnlyWithZeroBalanceInExactChangeOnlyState(){
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(CANDY, NUM_COINS_INITIALLY_STOCKED);
        vendingMachine.checkDisplay();
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("EXACT CHANGE ONLY", displayStatus);
    }
    
    @Test
    public void systemDisplaysExactChangeOnlyWhenExcessiveNonExactChangeUsedForColaPurchase(){
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(CANDY, NUM_COINS_INITIALLY_STOCKED);
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(COLA, 1);
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("EXACT CHANGE ONLY", displayStatus);
    }
    
    @Test
    public void systemDisplaysExactChangeOnlyWhenExcessiveNonExactChangeUsedForChipsPurchase(){
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(CANDY, NUM_COINS_INITIALLY_STOCKED);
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(CHIPS, 1);
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("EXACT CHANGE ONLY", displayStatus);
    }
    
    @Test
    public void systemDisplaysExactChangeOnlyWhenExcessiveNonExactChangeUsedForCandyPurchase(){
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(CHIPS, NUM_COINS_INITIALLY_STOCKED);
        attemptProductPurchaseNumTimesWithExtraFiveCentsValue(CANDY, 1);
        String displayStatus = vendingMachine.checkDisplay();
        assertEquals("EXACT CHANGE ONLY", displayStatus);
    }
    
    @Test
    public void acceptedCoinsAreAddedToCoinInventoryBeforeChangeDispensed(){
        for (int i = 0; i < NUM_COINS_INITIALLY_STOCKED; i++){  //This depletes all Dimes from
            vendingMachine.insert(QUARTER, QUARTER, QUARTER);   //Coin inventory
            vendingMachine.attemptCandyPurchase();
        }
        vendingMachine.checkCoinReturn();
        vendingMachine.insert(QUARTER, QUARTER, DIME);
        vendingMachine.attemptChipsPurchase();
        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, DIME));
    }
    
    //END Exact Change Only Functionality Tests
    
    
    
    
    private void attemptProductPurchaseNumTimesWithExtraFiveCentsValue(Product selectedProduct, 
            int numTimesToPurchase){
        
        Map<Product, Runnable> productPurchaseMethods = new HashMap<Product, Runnable>();
        productPurchaseMethods.put(COLA,  () -> vendingMachine.attemptColaPurchase());
        productPurchaseMethods.put(CHIPS, () -> vendingMachine.attemptChipsPurchase());
        productPurchaseMethods.put(CANDY, () -> vendingMachine.attemptCandyPurchase());
        
        for (int i = 0; i < numTimesToPurchase; i++){
            insertExtraFiveCentsForProductPurchase(selectedProduct);
            productPurchaseMethods.get(selectedProduct).run();
        }
    }
    
    private void insertExtraFiveCentsForProductPurchase(Product selectedProduct){
        Map<Product, Coin[]> extraFiveCentsForProductPurchase = new HashMap<Product, Coin[]>();
        extraFiveCentsForProductPurchase.put(CHIPS, new Coin[] {QUARTER, DIME, DIME, DIME});
        extraFiveCentsForProductPurchase.put(CANDY, new Coin[] {QUARTER, QUARTER, DIME, DIME});
        extraFiveCentsForProductPurchase.put(COLA, new Coin[] {QUARTER, QUARTER, QUARTER, DIME, DIME, DIME});
        
        vendingMachine.insert(extraFiveCentsForProductPurchase.get(selectedProduct));
    }


}
