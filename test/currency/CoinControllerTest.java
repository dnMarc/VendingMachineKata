package currency;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static currency.Coin.*;
import static currency.CoinController.*;

public class CoinControllerTest {
    
    private CoinController coinController;
    
    @Before
    public void setup(){
        coinController = new CoinController();
    }

    //BEGIN Accept Coins Functionality Tests
    
    @Test
    public void systemValueIsZeroCentsWithNoCoinsInserted() {
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(0, systemBalanceInCents);
    }
    
    @Test
    public void systemValueIsFiveCentsAfterOneNickelInserted(){
        coinController.insert(NICKEL);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(5, systemBalanceInCents);
    }
    
    @Test
    public void systemValueIsFifteenCentsAfterThreeNickelsInserted(){
        coinController.insert(NICKEL, NICKEL, NICKEL);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(15, systemBalanceInCents);
    }
    
    @Test
    public void systemValueIsTenCentsAfterOneDimeInserted(){
        coinController.insert(DIME);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(10, systemBalanceInCents);
    }
    
    @Test
    public void systemValueIsTwentyFiveCentsAfterOneQuarterInserted(){
        coinController.insert(QUARTER);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(25, systemBalanceInCents);
    }
    
    @Test
    public void systemValueIsZeroAfterOnePennyInserted(){
        coinController.insert(PENNY);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(0, systemBalanceInCents);
    }
    
    @Test
    public void systemValueCanReachValueOverNinetyNineCents(){
        coinController.insert(QUARTER, QUARTER, QUARTER, QUARTER, DIME, NICKEL, DIME);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(125, systemBalanceInCents);
    }
    
    @Test
    public void noReturnedCoinsWhenNoCoinsInserted(){
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void noReturnedCoinsWhenOnlyValidCoinsInserted(){
        coinController.insert(QUARTER, NICKEL, DIME, NICKEL);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void pennyReturnedtoCoinReturnWhenInserted(){
        coinController.insert(PENNY);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, PENNY));
    }
    
    @Test
    public void coinsInCoinReturnCanOnlyBeCollectedOnce(){
        coinController.insert(PENNY);
        coinController.getCoinsToDispense();
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    //END Accept Coins Functionality Tests
    
    //BEGIN Select Product Functionality Tests
    
    @Test
    public void systemBalanceZeroAfterResetting(){
        coinController.insert(QUARTER);
        coinController.resetSystemBalanceToZero();
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(0, systemBalanceInCents);
    }
    
    //END Select Product Functionality Tests
    
    //BEGIN Make Change Functionality Tests
    
    @Test
    public void dispenseNoCoinsIfExcessValueInsertedIsZero(){
        coinController.dispenseChange(0);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void dispenseNickelIfExcessValueInsertedIsFiveCents(){
        coinController.dispenseChange(NICKEL_VALUE_IN_CENTS);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, NICKEL));
    }
    
    @Test
    public void dispenseDimeIfExcessValueInsertedIsTenCents(){
        coinController.dispenseChange(DIME_VALUE_IN_CENTS);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, DIME));
    }
    
    @Test
    public void dispenseQuarterIfExcessValueInsertedIsTwentyFiveCents(){
        coinController.dispenseChange(QUARTER_VALUE_IN_CENTS);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, QUARTER));
    }
    
    @Test
    public void dispenseMultipleCoinsFromExcessValueInserted(){
        coinController.dispenseChange(QUARTER_VALUE_IN_CENTS + NICKEL_VALUE_IN_CENTS);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, QUARTER, NICKEL));
    }
    
    //END Make Change Functionality Tests
    
    //BEGIN Return Coins Functionality Tests
    
    @Test
    public void noCoinsManuallyReturnedWhenNoCoinsInserted(){
        coinController.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void nickelManuallyReturned(){
        coinController.insert(NICKEL);
        coinController.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, NICKEL));
    }
    
    @Test
    public void dimeManuallyReturned(){
        coinController.insert(DIME);
        coinController.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, DIME));
    }
    
    @Test
    public void coinsCanBeManuallyReturnedOnlyOnce(){
        coinController.insert(DIME, NICKEL);
        coinController.manuallyReturnAllInsertedCoins();
        coinController.getCoinsToDispense();
        coinController.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    @Test
    public void mixOfCoinsCanBeManuallyReturned(){
        coinController.insert(QUARTER, DIME, NICKEL, DIME);
        coinController.manuallyReturnAllInsertedCoins();
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinsReturnedAreAsExpected(returnedCoins, QUARTER, DIME, DIME, NICKEL));
    }
    
    @Test
    public void systemBalanceResetToZeroAfterManualCoinReturn(){
        coinController.insert(QUARTER, DIME);
        coinController.manuallyReturnAllInsertedCoins();
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(0, systemBalanceInCents);
    }
    
    //END Return Coins Functionality Tests
    
    //BEGIN Exact Change Only Functionality Tests
    
    @Test
    public void systemNotInExactChangeOnlyStateWhenInitialized(){
        boolean exactChangeOnlyState = coinController.systemInExactChangeOnlyState();
        assertFalse(exactChangeOnlyState);
    }
    
    @Test
    public void systemInExactChangeOnlyStateWhenNoNickelsInInventory(){
        for (int i = 0; i < NUM_COINS_INITIALLY_STOCKED; i++){
            coinController.dispenseChange(NICKEL_VALUE_IN_CENTS);
        }
        boolean exactChangeOnlyState = coinController.systemInExactChangeOnlyState();
        assertTrue(exactChangeOnlyState);
    }
    
    @Test
    public void systemInExactChangeOnlyStateWithOneNickelZeroDimesInInventory(){
        for (int i = 0; i < NUM_COINS_INITIALLY_STOCKED - 1; i++){
            coinController.dispenseChange(NICKEL_VALUE_IN_CENTS + DIME_VALUE_IN_CENTS);
        }
        coinController.dispenseChange(DIME_VALUE_IN_CENTS);
        boolean exactChangeOnlyState = coinController.systemInExactChangeOnlyState();
        assertTrue(exactChangeOnlyState);
    }
    
    @Test
    public void zeroNickelsDispensedAsChangeWhenNickelsOutOfStock(){
        for (int i = 0; i < NUM_COINS_INITIALLY_STOCKED; i++){
            coinController.dispenseChange(NICKEL_VALUE_IN_CENTS);
        }
        coinController.getCoinsToDispense();
        coinController.dispenseChange(NICKEL_VALUE_IN_CENTS);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(returnedCoins.isEmpty());
    }
    
    
    
    
    
    public static boolean coinsReturnedAreAsExpected(List<Coin> returnedCoins, Coin ... expectedCoinTypes ){
        if (returnedCoins.size() == expectedCoinTypes.length){
            Map<Coin, Integer> expectedNumCoinTypes = new HashMap<Coin, Integer>();
            Map<Coin, Integer> actualNumCoinTypes   = new HashMap<Coin, Integer>();
            
            initializeCoinNumMapsWithZeros(expectedNumCoinTypes, actualNumCoinTypes);
            
            tabulateCoins(expectedNumCoinTypes, expectedCoinTypes);
            
            Coin[] arrayOfActualReturnedCoins = returnedCoins.toArray(new Coin[0]);
            tabulateCoins(actualNumCoinTypes, arrayOfActualReturnedCoins);
            
            if (expectedNumCoinTypes.equals(actualNumCoinTypes)){
                return true;
            }
        }
        return false;
    }

    private static void initializeCoinNumMapsWithZeros(Map<Coin, Integer> expectedNumCoinTypes,
            Map<Coin, Integer> actualNumCoinTypes) {
        for (Coin currentCoinType : Coin.values()){
            expectedNumCoinTypes.put(currentCoinType, 0);
            actualNumCoinTypes.put(currentCoinType, 0);
        }
    }
    
    private static void tabulateCoins(Map<Coin, Integer> numCoinsMap, Coin[] arrayOfCoinsToBeTabulated) {
        for (Coin currentCoin : arrayOfCoinsToBeTabulated){
            numCoinsMap.put(currentCoin, numCoinsMap.get(currentCoin) + 1);
        }
    }
    
    

}
