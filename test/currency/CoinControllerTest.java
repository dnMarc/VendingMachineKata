package currency;

import static org.junit.Assert.*;
import org.junit.Test;

import static currency.Coin.*;

public class CoinControllerTest {

    @Test
    public void systemValueIsZeroCentsWithNoCoinsInserted() {
        CoinController coinController = new CoinController();
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(0, systemBalanceInCents);
    }
    
    @Test
    public void systemValueIsFiveCentsAfterOneNickelInserted(){
        CoinController coinController = new CoinController();
        coinController.insert(NICKEL);
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(5, systemBalanceInCents);
    }

}
