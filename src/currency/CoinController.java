package currency;

import java.util.Map;
import java.util.HashMap;

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
            if (coinIsType(currentCoin, NICKEL)){
                systemBalanceInCents += NICKEL_VALUE_IN_CENTS;
            }
            else if (coinIsType(currentCoin, DIME)){
                systemBalanceInCents += DIME_VALUE_IN_CENTS;
            }
            else{
                systemBalanceInCents += QUARTER_VALUE_IN_CENTS;
            }
        }
    }
    
    private boolean coinIsType(Coin coinToCheck, Coin referenceCoinType){
        if ((coinToCheck.getWeightInGrams() == referenceCoinType.getWeightInGrams()) &&
            (coinToCheck.getDiameterInMM()  == referenceCoinType.getDiameterInMM()) &&
            (coinToCheck.getThicknessInMM() == referenceCoinType.getThicknessInMM())){
            
            return true;
            }
        return false;
    }

 

    
    
}
