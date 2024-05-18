import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.Inbox;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.mailslurp.clients.ApiClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    public WebDriver driver;
    private static final String YOUR_API_KEY = "0e2ea70ac05b83c0fb6424128f26a3ab2ba04de66ae3d7e6f922310afd807f06";

    private static final Long TIMEOUT_MILLIS = 30000L;
    private static ApiClient mailslurpClient;

    private static Email email;
    private static Inbox inbox;
    private RegistrationPage registrationPage;

    @Before
    public void setup() throws MalformedURLException, ApiException {
        mailslurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
        mailslurpClient.setApiKey(YOUR_API_KEY);
        mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);

        InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
        inbox = inboxControllerApi.createInbox(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Test
    @Description("The test try to register and check whether the confirmation email was sent")
    public void testSuccessfulRegistration() throws ApiException {
        registrationPage.setEmail(inbox.getEmailAddress());
        registrationPage.Register();
        assertTrue(CheckEmails());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    private boolean CheckEmails() throws ApiException {
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
        email = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS, true);
        return (email.getSubject().contains("Rock Castle"));
    }
}
