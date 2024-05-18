import org.openqa.selenium.WebDriver;


class BandsPage extends PageBase {

    public BandsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/kapely");
    }

}
