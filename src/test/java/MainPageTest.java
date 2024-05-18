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

import static org.junit.Assert.assertEquals;

public class MainPageTest {
    public WebDriver driver;

    MainPage mainPage;

    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @Test
    @Description("The test checks whether the title of the page is correct")
    public void testPageTitle() {
        assertEquals(driver.getTitle(), "Home | Rock Castle");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
