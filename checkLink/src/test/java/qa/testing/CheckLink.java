package qa.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CheckLink {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Before
    public void login() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkLink(){
        driver.findElement(By.className("dataTable")).findElements(By.xpath("tbody/tr[@class='row']/td[7]")).get(0).click();
        List<WebElement> linkList = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
        for (WebElement el : linkList){
            String handle = driver.getWindowHandle();
            el.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            handles.remove(handle);
            driver.switchTo().window(handles.get(0));
            driver.close();
            driver.switchTo().window(handle);

        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
