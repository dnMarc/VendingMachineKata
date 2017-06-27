package currency;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


import static currency.Coin.*;

public class CoinController {
    
    private static final int PENNY_VALUE_IN_CENTS   = 0;
    public static final int NICKEL_VALUE_IN_CENTS  = 5;
    public static final int DIME_VALUE_IN_CENTS    = 10;
    public static final int QUARTER_VALUE_IN_CENTS = 25;
    private int systemBalanceInCents = 0;
    
    private List<Coin> coinsWaitingToBeReturned  = new ArrayList<Coin>();
    
    private Map<Coin, Integer> coinValuesInCents = new HashMap<Coin, Integer>();

    public CoinController(){
        initializeCoinValuesInCents();
    }

    private void initializeCoinValuesInCents() {
        coinValuesInCents.put(NICKEL, NICKEL_VALUE_IN_CENTS);
        coinValuesInCents.put(DIME, DIME_VALUE_IN_CENTS);
        coinValuesInCents.put(QUARTER, QUARTER_VALUE_IN_CENTS);
        coinValuesInCents.put(PENNY, PENNY_VALUE_IN_CENTS);
    }
    
    public int getSystemBalanceInCents() {
        return systemBalanceInCents;
    }
    
    public void resetSystemBalanceToZero() {
        systemBalanceInCents = 0;
    }

    public void insert(Coin ... insertedCoins) {
        for (Coin currentCoin : insertedCoins){
            Coin determinedCoinType = determineCoinType(currentCoin);
            if (determinedCoinType != PENNY){
                systemBalanceInCents += coinValuesInCents.get(determinedCoinType);
            }
            else{
                coinsWaitingToBeReturned.add(currentCoin);
            }
        }
    }
    
    private Coin determineCoinType(Coin coinToCheck){
        for (Coin currentCoinType : Coin.values()){
            if (coinIsType(coinToCheck, currentCoinType)){
                return currentCoinType;
            }
        }
        return PENNY;
    }
    
    private boolean coinIsType(Coin coinToCheck, Coin referenceCoinType){
        if ((coinToCheck.getWeightInGrams() == referenceCoinType.getWeightInGrams()) &&
            (coinToCheck.getDiameterInMM()  == referenceCoinType.getDiameterInMM()) &&
            (coinToCheck.getThicknessInMM() == referenceCoinType.getThicknessInMM())){
            
            return true;
            }
        return false;
    }
    
    public void dispenseChange(int excessPurchaseValueInserted) {
        while (excessPurchaseValueInserted > 0){
            if (excessPurchaseValueInserted >= QUARTER_VALUE_IN_CENTS){
                excessPurchaseValueInserted -= dispenseCoin(QUARTER);
            }
            else if (excessPurchaseValueInserted >= DIME_VALUE_IN_CENTS){
                excessPurchaseValueInserted -= dispenseCoin(DIME);
            }
            else if (excessPurchaseValueInserted >= NICKEL_VALUE_IN_CENTS){
                excessPurchaseValueInserted -= dispenseCoin(NICKEL);
            }
        }
    }
    
    private int dispenseCoin(Coin coinToBeDispensed){
        coinsWaitingToBeReturned.add(coinToBeDispensed);
        int valueInCentsOfDispensedCoin = coinValuesInCents.get(coinToBeDispensed);
        return valueInCentsOfDispensedCoin;
    }
    
    public void manuallyReturnAllInsertedCoins() {
        if (systemBalanceInCents > 0){
            coinsWaitingToBeReturned.add(NICKEL);
        }
    }

    public List<Coin> getCoinsToDispense() {
        List<Coin> returnedCoins = new ArrayList<Coin>(coinsWaitingToBeReturned);
        coinsWaitingToBeReturned.clear();
        return returnedCoins;
    }

    

    

 

    
    
}
