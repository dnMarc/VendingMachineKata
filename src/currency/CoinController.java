package currency;

import java.util.Map;
import java.util.HashMap;

import static currency.CoinDimensions.*;
import static currency.Coin.*;

public class CoinController {
    
    private static final int NICKEL_VALUE_IN_CENTS = 5;
    private static final int DIME_VALUE_IN_CENTS = 10;
    private static final int QUARTER_VALUE_IN_CENTS = 25;
    private int systemBalanceInCents = 0;
    
    private Map<Coin, Integer> coinValuesInCents = new HashMap<Coin, Integer>();

    public CoinController(){
        initializeCoinValuesInCents();
    }

    private void initializeCoinValuesInCents() {
        coinValuesInCents.put(NICKEL, NICKEL_VALUE_IN_CENTS);
        coinValuesInCents.put(DIME, DIME_VALUE_IN_CENTS);
        coinValuesInCents.put(QUARTER, QUARTER_VALUE_IN_CENTS);
    }
    
    public int getSystemBalanceInCents() {
        return systemBalanceInCents;
    }

    public void insert(Coin ... insertedCoins) {
        for (Coin currentCoin : insertedCoins){
            if (coinIsANickel(currentCoin)){
                systemBalanceInCents += NICKEL_VALUE_IN_CENTS;
            }
            else if (coinIsADime(currentCoin)){
                systemBalanceInCents += DIME_VALUE_IN_CENTS;
            }
            else{
                systemBalanceInCents += QUARTER_VALUE_IN_CENTS;
            }
        }
    }


    private boolean coinIsANickel(Coin currentCoin) {
        if ((currentCoin.getWeightInGrams() == NICKEL_WEIGHT_IN_GRAMS) &&
            (currentCoin.getDiameterInMM()  == NICKEL_DIAMETER_IN_MM) &&
            (currentCoin.getThicknessInMM() == NICKEL_THICKNESS_IN_MM)){
            
            return true;
        }
        return false;
    }
    
    private boolean coinIsADime(Coin currentCoin) {
        if ((currentCoin.getWeightInGrams() == DIME_WEIGHT_IN_GRAMS) &&
            (currentCoin.getDiameterInMM()  == DIME_DIAMETER_IN_MM) &&
            (currentCoin.getThicknessInMM() == DIME_THICKNESS_IN_MM)){
            
            return true;
        }
        return false;
    }

    
    
}
