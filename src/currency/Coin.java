package currency;

//all valid Coin type values were obtained from 
//https://www.usmint.gov/learn/coin-and-medal-programs/coin-specifications

public enum Coin {
    
    NICKEL(5.000, 21.21, 1.95),
    
    DIME(0.0, 0.0, 0.0);
    
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
