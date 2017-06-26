package vending;

public class VendingMachine {

    public String createSystemDisplay(int systemBalanceInCents) {
        if (systemBalanceInCents > 0){
            int numDollars  = systemBalanceInCents / 100;
            int numCents    = systemBalanceInCents % 100;
            return "$" + Integer.toString(numDollars) + "." +
                    String.format("%02d", numCents);
        }
        return "INSERT COIN";
    }

}
