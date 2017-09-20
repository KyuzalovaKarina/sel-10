package qa.testing.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import qa.testing.pages.BasketPage;
import qa.testing.pages.MainPage;
import qa.testing.pages.ProductPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Application {

    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void workWithBasket(){
        for (int count = 0; count < 3; count++) {
            mainPage.open();
            mainPage.selectProduct();
            productPage.checkCountProducts(count);
            productPage.addProduct();
            productPage.checkCountProducts(count+1);
        }
        basketPage.openBasket();
        for (int count = 1; count <= 3; count++) {
            basketPage.removeProduct();
        }
    }



    public void quit() {
        driver.quit();
    }



}
