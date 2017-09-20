package qa.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "remove_cart_item")
    public WebElement btnRemove;

    public void openBasket(){
        driver.get("http://localhost/litecart/en/checkout");
    }


    public void removeProduct(){
        wait.until(ExpectedConditions.visibilityOf(btnRemove)).click();
    }
}
