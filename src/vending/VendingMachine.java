package vending;

import java.util.List;
import java.util.ArrayList;

import currency.Coin;
import currency.CoinController;

public class VendingMachine {
    
    public static final int COLA_COST_IN_CENTS  = 100;
    public static final int CHIPS_COST_IN_CENTS = 50;
    
    private List<Product> productsToDispense = new ArrayList<Product>();
    
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

    public List<Coin> checkCoinReturn() {
        return coinManager.getCoinsToDispense();
    }

    public void attemptProductPurchase(Product selectedProduct, int systemBalanceInCents) {
        if (systemBalanceInCents >= selectedProduct.getCostInCents()){
            productsToDispense.add(selectedProduct);
        }
    }
    
    public List<Product> checkProductReturn() {
        return productsToDispense;
    }

}
