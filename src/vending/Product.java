package vending;

public enum Product {
    COLA(100),
    
    CHIPS(50);
    
    public int costInCents;
    
    Product(int cost){
        this.costInCents = cost;
    }
}
