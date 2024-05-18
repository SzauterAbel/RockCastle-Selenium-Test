import org.openqa.selenium.WebDriver;


class AboutFestivalPage extends PageBase {

    public AboutFestivalPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/o-festivalu");
    }

}
