import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;


class MainPage extends PageBase {
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.rockcastle.cz/en");
    }

}
