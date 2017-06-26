package currency;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoinControllerTest {

    @Test
    public void systemValueIsZeroCentsWithNoCoinsInserted() {
        CoinController coinController = new CoinController();
        int systemBalanceInCents = coinController.getSystemBalanceInCents();
        assertEquals(0, systemBalanceInCents);
    }

}
