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

}
