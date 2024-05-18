import org.openqa.selenium.WebDriver;


class LineUpPage extends PageBase {

    public LineUpPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en/program");
    }

}
