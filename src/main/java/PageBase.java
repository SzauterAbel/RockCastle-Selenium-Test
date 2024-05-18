import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static By logo = By.xpath("//a//img[contains(@src, 'logo.svg')]");
    public static By line_up_link = By.xpath("//li//a[@href='/en/program']");
    public static By bands_link = By.xpath("//li//a[@href='/en/kapely']");
    public static By merch_link = By.xpath("//li//a[@href='/en/e-shop']");
    public static By news_link = By.xpath("//li//a[@href='/en/aktuality']");
    public static By about_festival_link = By.xpath("//li//a[@href='/en/o-festivalu']");

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
   
}
