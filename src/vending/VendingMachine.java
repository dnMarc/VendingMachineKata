package vending;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import currency.Coin;
import currency.CoinController;
import static vending.Product.*;

public class VendingMachine {
    
    public static final int COLA_COST_IN_CENTS          = 100;
    public static final int CHIPS_COST_IN_CENTS         = 50;
    public static final int CANDY_COST_IN_CENTS         = 65;
    public static final int NUM_UNITS_INITIALLY_STOCKED = 20;
    
    private String textWaitingForDisplay = "";
    
    private List<Product> productsToDispense = new ArrayList<Product>();
    
    private Map<Product, Integer> productQuantitiesInStock = new HashMap<Product, Integer>();
    
    private CoinController coinManager = new CoinController();
    
    public VendingMachine(){
        stockAllProducts();
    }
    
    private void stockAllProducts() {
        for (Product currentProduct : Product.values()){
            productQuantitiesInStock.put(currentProduct, NUM_UNITS_INITIALLY_STOCKED);
        }
    }

    public String checkDisplay() {
        return createSystemDisplay(coinManager.getSystemBalanceInCents(), 
                    coinManager.systemInExactChangeOnlyState());
    }

    public String createSystemDisplay(int systemBalanceInCents, boolean exactChangeOnlyState) {
        if (textWaitingForDisplay != ""){
            return consumeMessageWaitingForDisplay();
        }
        if (systemBalanceInCents > 0){
            return createFormattedCurrencyDisplay(systemBalanceInCents);
        }
        if (exactChangeOnlyState){
            return "EXACT CHANGE ONLY";
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
    
    public void manuallyReturnAllInsertedCoins() {
        coinManager.manuallyReturnAllInsertedCoins();
    }

    public List<Coin> checkCoinReturn() {
        return coinManager.getCoinsToDispense();
    }
    
    public void attemptColaPurchase() {
        attemptProductPurchase(COLA,    coinManager.getSystemBalanceInCents(), false);
    }
    
    public void attemptChipsPurchase() {
        attemptProductPurchase(CHIPS,   coinManager.getSystemBalanceInCents(), false);
    }
    
    public void attemptCandyPurchase() {
        attemptProductPurchase(CANDY,   coinManager.getSystemBalanceInCents(), false);
    }

    public void attemptProductPurchase(Product selectedProduct, int systemBalanceInCents, 
            boolean exactChangeOnlyState) {
        if (productInStock(selectedProduct)){
            int productCost = selectedProduct.getCostInCents();
            if (systemBalanceInCents >= productCost){
                if (exactChangeOnlyState && systemBalanceInCents > productCost){
                    displayMessage("EXACT CHANGE ONLY");
                }
                else{
                    completeProductPurchase(selectedProduct, systemBalanceInCents);
                }
            }
            else{
                displayMessage("PRICE " + createFormattedCurrencyDisplay(productCost));
            }
        }
        else{
            displayMessage("SOLD OUT");
        }
    }
    
    private boolean productInStock(Product selectedProduct){
        if (productQuantitiesInStock.get(selectedProduct) > 0){
            return true;
        }
        return false;
    }

    private void completeProductPurchase(Product selectedProduct, int systemBalanceInCents) {
        dispenseProduct(selectedProduct);
        displayMessage("THANK YOU");
        int excessPurchaseValueInCents = calculateExcessValue(selectedProduct, systemBalanceInCents);
        coinManager.dispenseChange(excessPurchaseValueInCents);
        coinManager.resetSystemBalanceToZero();
        removeProductFromInventory(selectedProduct);
    }

    private void dispenseProduct(Product productToDispense){
        productsToDispense.add(productToDispense);
    }
    
    private void displayMessage(String messageToDisplay){
        textWaitingForDisplay = messageToDisplay;
    }
    
    public int calculateExcessValue(Product selectedProduct, int valueInCentsUsedForPurchase) {
        int productCostInCents = selectedProduct.getCostInCents();
        return valueInCentsUsedForPurchase - productCostInCents;
    }
    
    private void removeProductFromInventory(Product selectedProduct) {
        productQuantitiesInStock.put(selectedProduct, productQuantitiesInStock.get(selectedProduct) - 1);
    }
    
    public List<Product> checkProductReturn() {
        List<Product> dispensedProducts = new ArrayList<Product>(productsToDispense);
        productsToDispense.clear();
        return dispensedProducts;
    }





}
