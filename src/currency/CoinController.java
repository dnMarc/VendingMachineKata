package currency;

public class CoinController {
    
    private static final int NICKEL_VALUE_IN_CENTS = 5;
    private int systemBalanceInCents = 0;

    public int getSystemBalanceInCents() {
        return systemBalanceInCents;
    }

    public void insert(Coin insertedCoin) {
        systemBalanceInCents += NICKEL_VALUE_IN_CENTS;
    }

}
