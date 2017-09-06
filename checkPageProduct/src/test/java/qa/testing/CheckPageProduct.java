package qa.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckPageProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startChrome(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    //@Before
    public void startFirefox(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    //@Before
    public void startIE(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        driver = new InternetExplorerDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkPageProduct(){
        driver.get("http://localhost/litecart/");
        WebElement product = driver.findElement(By.id("box-campaigns"))
                .findElement(By.xpath("div/ul/li[1]/a[@class='link']"));
        String name = product.findElement(By.className("name")).getText();
        WebElement priceEl = product.findElement(By.className("regular-price"));
        WebElement actionPriceEl = product.findElement(By.className("campaign-price"));
        String[] price = new String[4];
        String[] actionPrice = new String[4];
        price[0] = priceEl.getText();
        price[1] = priceEl.getTagName();
        price[2] = priceEl.getCssValue("color");
        price[3] = priceEl.getCssValue("font-size")
                .substring(0, priceEl.getCssValue("font-size").indexOf("px"));
        actionPrice[0] = actionPriceEl.getText();
        actionPrice[1] = actionPriceEl.getTagName();
        actionPrice[2] = actionPriceEl.getCssValue("color");
        actionPrice[3] = actionPriceEl.getCssValue("font-size")
                .substring(0, actionPriceEl.getCssValue("font-size").indexOf("px"));
        assertEquals("Обычная цена не перечеркнута", price[1], "s");
        assertEquals("Акционная цена не жирная", actionPrice[1], "strong");
        assertTrue("Цвет обыкновенной цены не серый",
                (Color.fromString(price[2]).getColor().getRed()==Color.fromString(price[2]).getColor().getBlue())
                        && (Color.fromString(price[2]).getColor().getBlue() == Color.fromString(price[2]).getColor().getGreen()));
        assertTrue("Цвет акционной цены не красный",
                (Color.fromString(actionPrice[2]).getColor().getBlue() == 0) &&
                        (Color.fromString(actionPrice[2]).getColor().getGreen() == 0));
        assertTrue("Шрифт акционной цены не больше обычной",
                Double.valueOf(actionPrice[3]) > Double.valueOf(price[3]));
        product.click();
        product = driver.findElement(By.id("box-product"));
        assertEquals("Названия товара не совпадают", name, product.findElement(By.className("title")).getText());
        priceEl = driver.findElement(By.className("regular-price"));
        actionPriceEl = driver.findElement(By.className("campaign-price"));
        assertEquals("Обычные цены товара не совпадают", price[0], priceEl.getText());
        assertEquals("Акционные цены товара не совпадают", actionPrice[0], actionPriceEl.getText());
        price[0] = priceEl.getText();
        price[1] = priceEl.getTagName();
        price[2] = priceEl.getCssValue("color");
        price[3] = priceEl.getCssValue("font-size")
                .substring(0, priceEl.getCssValue("font-size").indexOf("px"));
        actionPrice[0] = actionPriceEl.getText();
        actionPrice[1] = actionPriceEl.getTagName();
        actionPrice[2] = actionPriceEl.getCssValue("color");
        actionPrice[3] = actionPriceEl.getCssValue("font-size")
                .substring(0, actionPriceEl.getCssValue("font-size").indexOf("px"));
        assertEquals("Обычная цена не перечеркнута", price[1], "s");
        assertEquals("Акционная цена не жирная", actionPrice[1], "strong");
        assertTrue("Цвет обыкновенной цены не серый",
                (Color.fromString(price[2]).getColor().getRed()==Color.fromString(price[2]).getColor().getBlue())
                        && (Color.fromString(price[2]).getColor().getBlue() == Color.fromString(price[2]).getColor().getGreen()));
        assertTrue("Цвет акционной цены не красный",
                (Color.fromString(actionPrice[2]).getColor().getBlue() == 0) &&
                        (Color.fromString(actionPrice[2]).getColor().getGreen() == 0));
        assertTrue("Шрифт акционной цены не больше обычной",
                Double.valueOf(actionPrice[3]) > Double.valueOf(price[3]));

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
