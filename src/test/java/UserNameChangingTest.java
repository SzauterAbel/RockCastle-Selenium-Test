import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class UserNameChangingTest {
    public WebDriver driver;

    private final String email = "test.user.selenium24@gmail.com";
    private final String password = "Test123";
    LoginPage loginPage;

    By successful_login_element = By.xpath("//div//i[contains(@class, 'mdi-account-edit-outline')]");
    By input_first_name = By.id("input-firstname");
    By button_save = By.xpath("//div//input[contains(@class, 'btn-success')]");
    By successful_logout_element = By.xpath("//li//a//i[contains(@class, 'mdi-account-lock-outline')]");

    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("The test try to log in and change the current username to something different")
    public void testChangeFirstname() {
        loginPage.Login(email, password);
        loginPage.waitAndReturnElement(successful_login_element);
        driver.findElement(successful_login_element).click();
        WebElement first_name_element = loginPage.waitAndReturnElement(input_first_name);
        if (Objects.equals(first_name_element.getText(), "User")) {
            first_name_element.sendKeys("Test");
        } else if (Objects.equals(first_name_element.getText(), "Test")) {
            first_name_element.sendKeys("User");
        }
        loginPage.waitAndReturnElement(button_save).click();
        loginPage.waitAndReturnElement(By.xpath("//div//div[contains(@class, 'alert-success')]"));
        WebElement return_link = driver.findElement(By.xpath("//div//a[contains(@class, 'btn-info')]"));
        return_link.click();
        WebElement logout_button = driver.findElement(By.xpath("//li//a//i[contains(@class, 'mdi-logout-variant')]"));
        logout_button.click();
        loginPage.waitAndReturnElement(successful_logout_element);
        assertTrue(driver.findElement(successful_logout_element).isDisplayed());
    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
