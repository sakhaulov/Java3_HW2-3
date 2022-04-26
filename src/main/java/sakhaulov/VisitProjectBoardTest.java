package sakhaulov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class VisitProjectBoardTest {

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
            webElement = driver.findElement(By.xpath(".//div[contains(text(), 'tsakhaulov')]"));
            webElement.click();

            //Projects page
            webElement = driver.findElement(By.xpath(".//span[contains(text(), 'sakhaulov')]"));
            webElement.click();

            //Projects page
            List<WebElement> webElements = driver.findElements(By.xpath(".//h1[contains(text(), 'SAKH board')]"));
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
