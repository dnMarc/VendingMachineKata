package currency;

import static currency.CoinDimensions.*;

//all valid Coin type values were obtained from 
//https://www.usmint.gov/learn/coin-and-medal-programs/coin-specifications

public enum Coin {
    
    NICKEL(NICKEL_WEIGHT_IN_GRAMS, NICKEL_DIAMETER_IN_MM, NICKEL_THICKNESS_IN_MM),
    
    DIME(2.268, 17.91, 1.35),
    
    QUARTER(0.0, 0.0, 0.0);
    
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
