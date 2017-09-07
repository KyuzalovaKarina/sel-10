package qa.testing;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class AddProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Before
    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void addProduct(){
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();
        driver.findElement(By.xpath("//input[@name='status' and @value='1']")).click();
        String productName = RandomStringUtils.randomAlphabetic(10);
        driver.findElement(By.xpath("//input[contains(@name,'name')]")).sendKeys(productName);
        driver.findElement(By.name("code"))
                .sendKeys(RandomStringUtils.randomNumeric(5));
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("10");
        driver.findElement(By.name("new_images[]"))
                .sendKeys(new File("src/main/resources/images/duck.jpg").getAbsolutePath());
        driver.findElement(By.cssSelector("a[href*='#tab-information']")).click();
        Select manuSelect = new Select(driver.findElement(By.name("manufacturer_id")));
        manuSelect.selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[contains(@name,'short_description')]")).sendKeys("short description");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("description");
        driver.findElement(By.cssSelector("a[href*='#tab-prices']")).click();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("20");
        driver.findElement(By.name("prices[USD]")).clear();
        driver.findElement(By.name("prices[USD]")).sendKeys("20");
        driver.findElement(By.name("save")).click();
        assertTrue("Продукт не отображен",
                driver.findElements(By.xpath("//tr[@class='row']/td[3]/a[text()='"+productName+"']")).size() > 0);


    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
