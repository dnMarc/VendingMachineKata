package currency;

import static currency.CoinDimensions.*;

//all valid Coin type values were obtained from 
//https://www.usmint.gov/learn/coin-and-medal-programs/coin-specifications

public enum Coin {
    
    NICKEL( NICKEL_WEIGHT_IN_GRAMS, NICKEL_DIAMETER_IN_MM,  NICKEL_THICKNESS_IN_MM),
    
    DIME(   DIME_WEIGHT_IN_GRAMS,   DIME_DIAMETER_IN_MM,    DIME_THICKNESS_IN_MM),
    
    QUARTER(5.670, 24.26, 1.75),
    
    PENNY(0.0, 0.0, 0.0);
    
    private final double weightInGrams;
    private final double diameterInMM;
    private final double thicknessInMM;
    
    Coin(double weight, double diameter, double thickness){
        this.weightInGrams = weight;
        this.diameterInMM = diameter;
        this.thicknessInMM = thickness;
    }
    
    public double getWeightInGrams(){
        return weightInGrams;
    }
    
    public double getDiameterInMM(){
        return diameterInMM;
    }
    
    public double getThicknessInMM(){
        return thicknessInMM;
    }
}
