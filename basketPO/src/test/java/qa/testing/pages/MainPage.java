package qa.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "box-most-popular")
    public WebElement tblMostPopular;

    public void open(){
        driver.get("http://localhost/litecart");
    }

    public void selectProduct(){
        tblMostPopular.findElement(By.xpath("div/ul/li[1]")).click();
    }


}
