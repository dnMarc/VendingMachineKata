package vending;

import static vending.VendingMachine.*;

public enum Product {
    COLA(COLA_COST_IN_CENTS),
    
    CHIPS(CHIPS_COST_IN_CENTS),
    
    CANDY(CANDY_COST_IN_CENTS);
    
    private final int costInCents;
    
    Product(int cost){
        this.costInCents = cost;
    }
    
    public int getCostInCents(){
        return costInCents;
    }
}
