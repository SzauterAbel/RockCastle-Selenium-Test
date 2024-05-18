import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {

    WebElement login_email;
    WebElement login_password;
    WebElement button;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/prihlaseni");

        login_email = driver.findElement(By.id("input-email"));
        login_password = driver.findElement(By.id("input-password"));
        button = driver.findElement(By.xpath("//div//button[contains(@class, 'btn-success')]"));
    }

    public void Login(String email, String password) {
        login_email.sendKeys(email);
        login_password.sendKeys(password);
        button.click();
    }
}