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
            else{
                systemBalanceInCents += DIME_VALUE_IN_CENTS;
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

    
    
}
