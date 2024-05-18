import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;


public class LoginTest {
    public WebDriver driver;

    private final String email = "test.user.selenium24@gmail.com";
    private final String password = "Test123";
     LoginPage loginPage;

     By successful_login_element = By.xpath("//div//i[contains(@class, 'mdi-account-edit-outline')]");
     By successful_logout_element = By.xpath("//li//a//i[contains(@class, 'mdi-account-lock-outline')]");

    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }
    
    @Test
    @Description("The test try to log in and log out with right credentials")
    public void testSuccessfulLoginAndLogout() {
        loginPage.Login(email, password);
        loginPage.waitAndReturnElement(successful_login_element);
        assertTrue(driver.findElement(successful_login_element).isDisplayed());
        WebElement return_link = driver.findElement(By.xpath("//div//a[contains(@class, 'btn-info')]"));
        return_link.click();
        WebElement logout_button = driver.findElement(By.xpath("//li//a//i[contains(@class, 'mdi-logout-variant')]"));
        logout_button.click();
        loginPage.waitAndReturnElement(successful_logout_element);
        assertTrue(driver.findElement(successful_logout_element).isDisplayed());
    }
    
    @Test(expected = org.openqa.selenium.TimeoutException.class)
    @Description("The test try to log in with wrong credentials and expects a timeout")
    public void testUnsuccessfulLogin() {
        loginPage.Login("wrong.email@gmail.com", "random_wrong_password");
        loginPage.waitAndReturnElement(successful_login_element);
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
