package vending;

public enum Product {
    COLA(100),
    
    CHIPS(50);
    
    private final int costInCents;
    
    Product(int cost){
        this.costInCents = cost;
    }
    
    public int getCostInCents(){
        return costInCents;
    }
}
