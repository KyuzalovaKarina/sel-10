package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ClickTabsTest {

        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void start(){
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }

        @Test
        public void login() {
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
        }

        @Test
        public void clickTabs(){
            //авторизация
            login();
            int size = driver.findElement(By.id("box-apps-menu")).findElements(By.tagName("li")).size();
            for(int i=1; i<=size;i++){
                driver.findElement(By.id("box-apps-menu")).findElement(By.xpath("./li["+i+"]")).click();
                if(driver.findElements(By.tagName("h1")).size()> 0){
                    System.out.println("Заголовок h1 найден");
                }
                if (driver.findElements(By.className("docs")).size() == 0)
                    continue;
                int subSize = driver.findElement(By.className("docs")).findElements(By.tagName("li")).size();
                for (int j=1; j<=subSize;j++){
                    driver.findElement(By.className("docs")).findElement(By.xpath("./li["+j+"]")).click();
                }
            }

        }

        @After
        public void stop(){
            driver.quit();
            driver = null;
        }
    }
