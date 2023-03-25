import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.*;

@RunWith(Parameterized.class)
public class OrderTests {
    public WebDriver driver;
        private String name;
        private String surname;
        private String address;
        private String metroStation;
        private String phone;
        private String commentField;

        public OrderTests( String name, String surname, String address, String metroStation,
                                     String phone, String comment) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metroStation = metroStation;
            this.phone = phone;
            this.commentField = comment;
        }

        @Parameterized.Parameters
        public static Object[][] getDataForForm() {
            return new Object[][]{
                    {"Мария", "Шульгина", "Оренбург", "Румянцево", "89128460009", "Описание заказа 1"},
                    {"Николай", "Ковальчук", "Воронеж", "Чистые пруды", "89999245432", "Описание заказа 1"},
            };
        }
    @Before
    public void openWebsite() {
        final String browser = CHROME;
        if (browser.equals(CHROME)) {
            //запустили хром
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser.equals(FIREFOX)) {
            System.setProperty("webdriver.gecko.driver","/Users/mshulgina/WebDriver/bin/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equals(EDGE)) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
        @Test
        // Тест для кнопки "Заказать" вверху страницы
        public void scooterButtonOrderOne () throws InterruptedException {
            MainPage mainPage = new MainPage(driver);
            //нажимаем на кнопку заказать вверху экрана
            mainPage.clickButtonOrderUp();
            //кликнули по кнопке куки
            mainPage.checkCookeIsDisplayed();
            OrderPage formForOrder = new OrderPage(driver);
            //заполняем форму заказа
            formForOrder.filling(name, surname, address, metroStation,
                    phone, commentField);
            formForOrder.checkStatus();
        }

        @Test
        // Тест для кнопки "Заказать" внизу траницы
        public void scooterButtonOrderTwo() {
            MainPage mainPage = new MainPage(driver);
            //кликнули по кнопке куки
            mainPage.checkCookeIsDisplayed();
            //скрол до нижней кнопки заказать
            //нажимаем на кнопку заказать внизу экрана
            mainPage.clickButtonOrderDown();
            OrderPage formForOrder = new OrderPage(driver);
            //заполняем форму заказа
            formForOrder.filling(name, surname, address, metroStation,
                    phone, commentField);
        }
    @After
    public void closeWebsite() {
        // Закрыть браузер
        driver.quit();
    }
    }

