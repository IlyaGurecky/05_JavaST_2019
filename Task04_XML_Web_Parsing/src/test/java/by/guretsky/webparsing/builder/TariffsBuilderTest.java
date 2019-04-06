package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.entity.CallPrices;
import by.guretsky.webparsing.entity.Calls;
import by.guretsky.webparsing.entity.Internet;
import by.guretsky.webparsing.entity.Parameters;
import by.guretsky.webparsing.entity.Tariff;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for test xml parsers.
 *
 * @author ilyaguretsky
 */
public class TariffsBuilderTest {
    /**
     * XML file path.
     */
    private static final String TEST_XML_PATH = "src"
            + File.separator + "main" + File.separator + "data"
            + File.separator + "test.xml";
    /**
     * {@link Internet} object.
     */
    private Tariff internet = new Internet();
    /**
     * {@link Calls} object.
     */
    private Tariff calls = new Calls();

    /**
     * Initialization method.
     *
     * @throws ParseException if date is incorrect
     */
    @BeforeClass
    public void init() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        internet.setName("Без границ");
        internet.setPayroll(10.0);
        internet.setOperator("life:)");
        internet.setTariffId("AB123");
        ((Internet) internet).setFreeMb(1000);
        ((Internet) internet).setSpeedLimit(100);
        internet.setTariffDate(format.parse("2019-04-21"));
        internet.setEndDate(format.parse("2024-10-10"));
        Parameters parameters = new Parameters();
        parameters.setBlockingWithDebt(true);
        parameters.setConnectionPrice(15.0);
        internet.setParameters(parameters);

        calls.setName("Смотри вперед");
        calls.setTariffId("VS124");
        calls.setOperator("Йота");
        calls.setEndDate(format.parse("2020-09-05"));
        calls.setTariffDate(format.parse("2017-10-18"));
        calls.setPayroll(5.0);
        parameters = new Parameters();
        parameters.setConnectionPrice(10.0);
        parameters.setBlockingWithDebt(false);
        calls.setParameters(parameters);
        ((Calls) calls).setSMSPrice(0.05);
        ((Calls) calls).setTariffication("minute");
        CallPrices callPrices = new CallPrices();
        callPrices.setLandLine(0.1);
        callPrices.setOutside(0.03);
        callPrices.setInside(0.01);
        ((Calls) calls).setCallPrices(callPrices);
    }

    /**
     * Test method for DOM parser.
     */
    @Test(description = "Positive test for DOM parser")
    public void testTariffsDOMBuilder() {
        List<Tariff> expectedTariffs = Arrays.asList(calls, internet);
        List<Tariff> actualTariffs = new Director().createTariffs(TEST_XML_PATH,
                new TariffsDOMBuilder());
        Assert.assertEquals(actualTariffs, expectedTariffs);
    }

    /**
     * Test method for SAX parser.
     */
    @Test(description = "Positive test for SAX parser")
    public void testTariffsSAXBuilder() {
        List<Tariff> expectedTariffs = Arrays.asList(internet, calls);
        List<Tariff> actualTariffs = new Director().createTariffs(TEST_XML_PATH,
                new TariffsSAXBuilder());
        Assert.assertEquals(actualTariffs, expectedTariffs);
    }

    /**
     * Test method for StAX parser.
     */
    @Test(description = "Positive test for StAX parser")
    public void testTariffsStAXBuilder() {
        List<Tariff> expectedTariffs = Arrays.asList(internet, calls);
        List<Tariff> actualTariffs = new Director().createTariffs(TEST_XML_PATH,
                new TariffsStAXBuilder());
        Assert.assertEquals(actualTariffs, expectedTariffs);
    }
}
