import org.openqa.selenium.WebDriver;


class MerchPage extends PageBase {

    public MerchPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/e-shop");
    }

}
