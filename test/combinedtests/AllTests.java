package combinedtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import currency.CoinControllerTest;
import vending.VendingMachineTest;
import combinedtests.IntegrationTests;

@RunWith(Suite.class)
@SuiteClasses({VendingMachineTest.class, CoinControllerTest.class, IntegrationTests.class})
public class AllTests {

}
