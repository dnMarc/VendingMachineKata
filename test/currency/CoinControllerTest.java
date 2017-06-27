package currency;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static currency.Coin.*;

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
        assertTrue(coinReturnedIsAsExpected(returnedCoins, PENNY));
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
        coinController.dispenseChange(5);
        List<Coin> returnedCoins = coinController.getCoinsToDispense();
        assertTrue(coinReturnedIsAsExpected(returnedCoins, NICKEL));
    }
    
    
    public static boolean coinReturnedIsAsExpected(List<Coin> returnedCoin, Coin expectedCoinType){
        if (returnedCoin.size() == 1){
            if (returnedCoin.get(0) == expectedCoinType){
                return true;
            }
        }
        return false;
    }

}
