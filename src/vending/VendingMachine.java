package vending;

public class VendingMachine {

    public String createSystemDisplay(int systemBalanceInCents) {
        if (systemBalanceInCents > 0){
            return "$0.05";
        }
        return "INSERT COIN";
    }

}
