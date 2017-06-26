package vending;

import currency.Coin;
import currency.CoinController;

public class VendingMachine {
    
    private CoinController coinManager = new CoinController();
    
    public String checkDisplay() {
        return createSystemDisplay(coinManager.getSystemBalanceInCents());
    }

    public String createSystemDisplay(int systemBalanceInCents) {
        if (systemBalanceInCents > 0){
            return createFormattedCurrencyDisplay(systemBalanceInCents);
        }
        return "INSERT COIN";
    }

    private String createFormattedCurrencyDisplay(int systemBalanceInCents) {
        int numDollars  = systemBalanceInCents / 100;
        int numCents    = systemBalanceInCents % 100;
        return "$" + Integer.toString(numDollars) + "." +
                String.format("%02d", numCents);
    }

    public void insert(Coin ... insertedCoins) {
        coinManager.insert(insertedCoins);
    }

}
