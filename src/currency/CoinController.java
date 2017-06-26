package currency;

import static currency.CoinDimensions.*;

public class CoinController {
    
    private static final int NICKEL_VALUE_IN_CENTS = 5;
    private static final int DIME_VALUE_IN_CENTS = 10;
    private int systemBalanceInCents = 0;

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
                systemBalanceInCents += 25;
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
        if ((currentCoin.getWeightInGrams() == 2.268) &&
            (currentCoin.getDiameterInMM()  == 17.91) &&
            (currentCoin.getThicknessInMM() == 1.35)){
            
            return true;
        }
        return false;
    }

    
    
}
