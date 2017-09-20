package qa.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "options[Size]")
    public WebElement cbSize;

    @FindBy(name = "add_cart_product")
    public WebElement btnAdd;

    @FindBy(xpath = "//span[@class='quantity']")
    public WebElement lblCount;

    public void addProduct(){
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            Select select = new Select(cbSize);
            select.selectByIndex(0);
        }
        btnAdd.click();
    }

    public void checkCountProducts(int count){
        wait.until(ExpectedConditions.textToBePresentInElement(lblCount, String.valueOf(count)));
    }
}
