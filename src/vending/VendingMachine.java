package vending;

import java.util.List;
import java.util.ArrayList;

import currency.Coin;
import currency.CoinController;
import static vending.Product.*;

public class VendingMachine {
    
    public static final int COLA_COST_IN_CENTS  = 100;
    public static final int CHIPS_COST_IN_CENTS = 50;
    public static final int CANDY_COST_IN_CENTS = 65;
    
    private String textWaitingForDisplay = "";
    
    private List<Product> productsToDispense = new ArrayList<Product>();
    
    private CoinController coinManager = new CoinController();
    
    public String checkDisplay() {
        return createSystemDisplay(coinManager.getSystemBalanceInCents());
    }

    public String createSystemDisplay(int systemBalanceInCents) {
        if (textWaitingForDisplay != ""){
            return consumeMessageWaitingForDisplay();
        }
        if (systemBalanceInCents > 0){
            return createFormattedCurrencyDisplay(systemBalanceInCents);
        }
        return "INSERT COIN";
    }

    private String consumeMessageWaitingForDisplay() {
        String displayBuffer = new String(textWaitingForDisplay);
        textWaitingForDisplay = "";
        return displayBuffer;
    }

    private String createFormattedCurrencyDisplay(int valueInCents) {
        int numDollars  = valueInCents / 100;
        int numCents    = valueInCents % 100;
        return "$" + Integer.toString(numDollars) + "." +
                String.format("%02d", numCents);
    }

    public void insert(Coin ... insertedCoins) {
        coinManager.insert(insertedCoins);
    }

    public List<Coin> checkCoinReturn() {
        return coinManager.getCoinsToDispense();
    }
    
    public void attemptColaPurchase() {
        attemptProductPurchase(COLA,    coinManager.getSystemBalanceInCents());
    }
    
    public void attemptChipsPurchase() {
        attemptProductPurchase(CHIPS,   coinManager.getSystemBalanceInCents());
    }

    public void attemptProductPurchase(Product selectedProduct, int systemBalanceInCents) {
        int productCost = selectedProduct.getCostInCents();
        if (systemBalanceInCents >= productCost){
            dispenseProduct(selectedProduct);
            displayMessage("THANK YOU");
        }
        else{
            displayMessage("PRICE " + createFormattedCurrencyDisplay(productCost));
        }
    }
    
    private void dispenseProduct(Product productToDispense){
        productsToDispense.add(productToDispense);
    }
    
    private void displayMessage(String messageToDisplay){
        textWaitingForDisplay = messageToDisplay;
    }
    
    public List<Product> checkProductReturn() {
        List<Product> dispensedProducts = new ArrayList<Product>(productsToDispense);
        productsToDispense.clear();
        return dispensedProducts;
    }



}
