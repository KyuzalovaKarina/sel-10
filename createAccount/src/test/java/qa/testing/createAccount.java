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

import java.util.Random;

public class createAccount {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startChrome(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void createAccount(){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//a[text()='Create Account']")).click();
        driver.findElement(By.name("firstname")).sendKeys("firstname");
        driver.findElement(By.name("lastname")).sendKeys("lastname");
        driver.findElement(By.name("address1")).sendKeys("address1");
        driver.findElement(By.name("postcode")).sendKeys(RandomStringUtils.randomNumeric(5));
        driver.findElement(By.name("city")).sendKeys("city");
        Select select = new Select(driver.findElement(By.name("country_code")));
        select.selectByVisibleText("United States");
        driver.findElement(By.name("phone")).sendKeys(RandomStringUtils.randomNumeric(10));
        String email = RandomStringUtils.randomAlphanumeric(10)+"@com";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.name("confirmed_password")).sendKeys("123456");
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
