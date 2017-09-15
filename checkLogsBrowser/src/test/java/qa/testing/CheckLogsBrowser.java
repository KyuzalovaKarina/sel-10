package qa.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class CheckLogsBrowser {
    private WebDriver driver;
    private WebDriverWait wait;

    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }

    @Before
    public void start(){
        driver = new EventFiringWebDriver(new ChromeDriver());
        wait = new WebDriverWait(driver, 10);
    }

    @Before
    public void login() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkLogsBrowser(){
        int count = 0;
        while (driver.findElement(By.className("dataTable")).findElements(By.xpath("tbody/tr[@class='row']/td[3]/i[@class='fa fa-folder']")).size() > 0){
            driver.findElement(By.className("dataTable")).findElement(By.xpath("tbody/tr[@class='row']/td[3]/a")).click();
        }
        count = driver.findElement(By.className("dataTable")).findElements(By.xpath("tbody/tr[@class='row']/td[3]/a")).size();
        for (int i=0; i<count; i++){
            while (driver.findElement(By.className("dataTable")).findElements(By.xpath("tbody/tr[@class='row']/td[3]/i[@class='fa fa-folder']")).size() > 0){
                driver.findElement(By.className("dataTable")).findElement(By.xpath("tbody/tr[@class='row']/td[3]/a")).click();
            }
            driver.findElement(By.className("dataTable")).findElements(By.xpath("tbody/tr[@class='row']/td[3]/a")).get(i).click();
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            assertTrue("Найдены логи в браузере! ", driver.manage().logs().get("browser").getAll().size() == 0);
        }
        driver.manage().logs().get("browser").getAll().forEach(l -> System.out.println(l));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
