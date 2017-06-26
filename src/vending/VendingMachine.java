package vending;

public class VendingMachine {

    public String createSystemDisplay(int systemBalanceInCents) {
        if (systemBalanceInCents > 0){
            return "$0." + String.format("%02d", systemBalanceInCents);
        }
        return "INSERT COIN";
    }

}
