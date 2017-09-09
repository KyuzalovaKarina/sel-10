package qa.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkWithBasket {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void workWithBasket(){
        for (int  i=1; i<=3; i++) {
            driver.get("http://localhost/litecart/");
            driver.findElement(By.id("box-most-popular")).findElement(By.xpath("div/ul/li[1]")).click();
            if (driver.findElements(By.name("options[Size]")).size() > 0) {
                Select select = new Select(driver.findElement(By.name("options[Size]")));
                select.selectByVisibleText("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            WebElement quantityEl = driver.findElement(By.id("cart")).findElement(By.className("quantity"));
            wait.until(ExpectedConditions.textToBePresentInElement(quantityEl, String.valueOf(i)));
        }
        driver.findElement(By.id("cart")).findElement(By.className("link")).click();
        while (driver.findElements(By.name("remove_cart_item")).size() > 0) {
            int countTr = driver.findElement(By.id("order_confirmation-wrapper")).findElements(By.xpath("table/tbody/tr/td[@class='item']")).size();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("remove_cart_item")))).click();
            if (driver.findElements(By.id("checkout-cart-wrapper")).size() > 0){
                break;
            }
            wait.until((WebDriver d) -> d.findElement(By.id("order_confirmation-wrapper")).findElements(By.xpath("table/tbody/tr/td[@class='item']")).size() == countTr - 1);
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
