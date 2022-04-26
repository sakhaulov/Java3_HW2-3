package sakhaulov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class AuthorizationWrongPassTest {

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
            webElement.sendKeys("TestSakhaulov202");
            webElement = driver.findElement(By.id("login-submit"));
            webElement.click();

            //Check error message
            List<WebElement> webElements = driver.findElements(By.xpath(".//*[@id='login-error']/span[contains(text(), 'Неверный адрес электронной почты и/или пароль.')]"));
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
