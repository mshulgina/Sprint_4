import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;/////commit
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

    public OrderTests(String name, String surname, String address, String metroStation,
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

    @Test
    // Тест для кнопки "Заказать" вверху страницы
    public void scooterButtonOrderOne() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        //нажимаем на кнопку заказать вверху экрана
        mainPage.clickButtonOrderUp();
        //кликнули по кнопке куки
        mainPage.checkCookeIsDisplayed();
        OrderPage formForOrder = new OrderPage(driver);
        //заполняем форму заказа
        formForOrder.filling(name, surname, address, metroStation,
                phone, commentField);
        String actualTextAnswer = driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
        Assert.assertTrue("Текст иной", actualTextAnswer.contains("Заказ оформлен"));
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
        String actualTextAnswer = driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
        Assert.assertTrue("Текст иной", actualTextAnswer.contains("Заказ оформлен"));
    }
}

