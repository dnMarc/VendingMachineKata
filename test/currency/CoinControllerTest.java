package currency;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        for (int i = 0; i < 3; i++){
            coinController.insert(NICKEL);
        }
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(15, systemBalanceInCents);
    }

}
