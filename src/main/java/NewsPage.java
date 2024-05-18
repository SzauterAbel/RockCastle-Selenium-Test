import org.openqa.selenium.WebDriver;


class NewsPage extends PageBase {

    public NewsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/aktuality");
    }

}
