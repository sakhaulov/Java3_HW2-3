package sakhaulov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ValidAuthorizationTest {
    public static void main(String[] args) {

        /*
        System.setProperty(
                "webdriver.chrome.driver",
                "src/main/resources/chromedriver");
         */

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.atlassian.com");

        try {
            //Authorization
            //Index page
            WebElement webElement = driver.findElement(By.xpath(".//span[contains(text(),'My account')]"));
            webElement.click();
            webElement = driver.findElement(By.xpath(".//span[contains(text(), 'Log in')]"));
            webElement.click();

            //Authorization form
            webElement = driver.findElement(By.name("username"));
            webElement.sendKeys("timur.sakhaulov@gmail.com");
            webElement = driver.findElement(By.cssSelector("span.css-19r5em7"));
            webElement.click();
            webElement = driver.findElement(By.id("password"));
            webElement.sendKeys("TestSakhaulov2022");
            webElement = driver.findElement(By.id("login-submit"));
            webElement.click();
            //Authorization end

            //Products page
            new WebDriverWait(driver, Duration.ofSeconds(3));
            List<WebElement> webElements = driver.findElements(By.xpath(".//*[contains(text(), 'sakhaulov')]"));
            if (webElements.size() > 0) {
                System.out.println("Test success");
            } else {
                System.out.println("Test fail");
            }

        } finally {
            driver.quit();
        }

    }
}
