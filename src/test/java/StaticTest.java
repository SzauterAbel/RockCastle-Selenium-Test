import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StaticTest {
    private WebDriver driver;
    private PageBase page;

    @Parameterized.Parameters(name = "{index}: Testing {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { MainPage.class },
            { LineUpPage.class },
            { BandsPage.class },
            { MerchPage.class },
            { NewsPage.class },
            { AboutFestivalPage.class }
        });
    }

    @Parameterized.Parameter
    public Class<? extends PageBase> pageClass;

    @Before
    public void setup() throws MalformedURLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        page = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    @Test
    public void testHeader() {
        assertTrue(driver.findElement(PageBase.logo).isDisplayed());
        assertTrue(driver.findElement(PageBase.line_up_link).isDisplayed());
        assertTrue(driver.findElement(PageBase.bands_link).isDisplayed());
        assertTrue(driver.findElement(PageBase.merch_link).isDisplayed());
        assertTrue(driver.findElement(PageBase.news_link).isDisplayed());
        assertTrue(driver.findElement(PageBase.about_festival_link).isDisplayed());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
