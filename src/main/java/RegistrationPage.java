import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends PageBase {

    protected String firstName = "Test";
    protected String lastName = "User";
    protected String email = "";
    protected String phone = "123456789";
    protected String password = "Password123";

    private WebElement reg_firstname;
    private WebElement reg_lastname;
    private WebElement reg_email;
    private WebElement reg_phone;
    private WebElement reg_password;
    private WebElement reg_password_conf;

    private WebElement terms_and_cond;
    private WebElement button;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/registrace");
    }

    public void Register() {
        reg_firstname = driver.findElement(By.id("input-firstname"));
        reg_lastname = driver.findElement(By.id("input-lastname"));
        reg_email = driver.findElement(By.id("input-email"));
        reg_phone = driver.findElement(By.id("input-telephone-2"));
        reg_password = driver.findElement(By.id("input-password"));
        reg_password_conf = driver.findElement(By.id("input-confirm"));

        terms_and_cond = driver.findElement(By.xpath("//div//input[@name='agree' and @type='checkbox']"));
        button = driver.findElement(By.xpath("//div//input[contains(@class, 'btn-primary')]"));


        reg_firstname.sendKeys(firstName);
        reg_lastname.sendKeys(lastName);
        reg_email.sendKeys(email);
        reg_phone.sendKeys(phone);
        reg_password.sendKeys(password);
        reg_password_conf.sendKeys(password);

        if (!terms_and_cond.isSelected()) {
            terms_and_cond.click();
        }

        button.click();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
