package ca.ulaval.glo4003.acceptanceTests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class TransactionsAreLoggedTest {

    private static final String TRANSACTIONS_LOG = "transactions.log";
    private static final String A_TICKET_QUANTITY = "1";
    private TestFixture fixture;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();
    }

    @After
    public void tearDown() {
        fixture.close();
    }

    @Test
    public void transactionGetsLoggedAfterPurchasingATicket() throws IOException {
        int initialSize = getLogSize();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.buySelectedTickets();
        fixture.payForTickets();
        int finalSize = getLogSize();

        assertTrue(finalSize > initialSize);
    }

    private int getLogSize() throws IOException {
        File logfile = new File(TRANSACTIONS_LOG);
        if (!logfile.exists()) {
            return 0;
        }
        LineNumberReader lnr = new LineNumberReader(new FileReader(logfile));
        lnr.skip(Long.MAX_VALUE);
        int number = lnr.getLineNumber();
        lnr.close();
        return number;
    }
}
