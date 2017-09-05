package qa.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckSortCountriesTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    public void login(String url) {
        driver.get(url);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkSortCountries(){
        login("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<String> countriesList = new ArrayList<>();
        List<WebElement> tdList = driver.findElement(By.className("dataTable")).findElements(By.className("row"));
        List<String> zonesList = new ArrayList<>();
        for (WebElement td : tdList){
            countriesList.add(td.findElement((By.xpath("td[5]/a"))).getText());
            String countZone = td.findElement((By.xpath("td[6]"))).getText();
            if (Integer.valueOf(countZone) > 0){
                zonesList.add(td.findElement((By.xpath("td[5]/a"))).getText());
            }
        }
        checkSortByAlphafit(countriesList);
        for(String zone : zonesList){
                driver.findElement(By.xpath("//td/a[text()='"+ zone+"']")).click();
                List<WebElement> subZonesElList = driver.findElement(By.id("table-zones"))
                        .findElements(By.xpath("tbody/tr/td/input[contains(@name,'[name]')]"));
                List<String> subZonesList = new ArrayList<>();
                for (WebElement el : subZonesElList){
                    subZonesList.add(el.getAttribute("value"));
                }
                checkSortByAlphafit(subZonesList);
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        }
        driver.close();

    }

    @Test
    public void checkSortZones(){
        login("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<String> countriesList = new ArrayList<>();
        List<WebElement> tdList = driver.findElement(By.className("dataTable")).findElements(By.className("row"));
        for (WebElement td : tdList){
            countriesList.add(td.findElement(By.xpath("td[3]")).getText());
        }
        checkSortByAlphafit(countriesList);
    }

    public void checkSortByAlphafit(List<String> countriesList){
        List<String> unsortedList = countriesList;
        Collections.sort(countriesList);
        List<String> sortedList = countriesList;
        assertEquals("Страны неотсортированы в алфавитном порядке", unsortedList, sortedList);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
