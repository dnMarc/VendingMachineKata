package combinedtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import currency.CoinControllerTest;
import vending.VendingMachineTest;

@RunWith(Suite.class)
@SuiteClasses({VendingMachineTest.class, CoinControllerTest.class})
public class AllTests {

}
