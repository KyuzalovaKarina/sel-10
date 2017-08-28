package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckStickerTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkSticker() {
        driver.get("http://localhost/litecart/");
        List<WebElement> listElement = driver.findElements(By.className("image-wrapper"));
        for (WebElement element : listElement){
            List<WebElement> stickers = element.findElements(By.xpath("div[contains(@class,'sticker')]"));
            if (stickers.size() == 1){
                System.out.println("Элемент имеет один стикер со значением " + stickers.get(0).getText());
            }

        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

