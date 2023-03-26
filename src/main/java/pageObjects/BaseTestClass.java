package pageObjects;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.remote.BrowserType.*;

public class BaseTestClass {
    public WebDriver driver;
    @Before
    public void openWebsite() {
        final String browser = CHROME;
        if (browser.equals(CHROME)) {
            //запустили хром
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser.equals(FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "/Users/mshulgina/WebDriver/bin/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equals(EDGE)) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @After
    public void closeWebsite() {
        // Закрыть браузер
        driver.quit();
    }
}
