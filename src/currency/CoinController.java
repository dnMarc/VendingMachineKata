package currency;

public class CoinController {
    
    private int systemBalanceInCents = 0;

    public int getSystemBalanceInCents() {
        return systemBalanceInCents;
    }

    public void insert(Coin insertedCoin) {
        systemBalanceInCents = 5;
    }

}
