package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    //локатор кнопка куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");
    //локатор для скролла вниз до вопроса МКАД
    private By questionsMKAD = By.id("accordion__heading-7");
    //Локатор для кнопки Заказать вверху экрана
    private static final By buttonOrderUp = By.className("Button_Button__ra12g");
    //Локатор для кнопки Заказать внизу экрана
    private static final By buttonOrderDown= By.className("Button_Button__ra12g Button_Middle__1CSJM");
    //локаторы для вопросов
    private static final By questionOne = By.id("accordion__heading-0");
    private static final By questionTwo = By.id("accordion__heading-1");
    private static final By questionThree = By.id("accordion__heading-2");
    private static final By questionFour = By.id("accordion__heading-3");
    private static final By questionFive = By.id("accordion__heading-4");
    private static final By questionSix = By.id("accordion__heading-5");
    private static final By questionSeven = By.id("accordion__heading-6");
    private static final By questionEight = By.id("accordion__heading-7");
    //локаторы для ответов
    private static final By answerOne = By.id("accordion__panel-0");
    private static final By answerTwo = By.id("accordion__panel-1");
    private static final By answerThree = By.id("accordion__panel-2");
    private static final By answerFour = By.id("accordion__panel-3");
    private static final By answerFive = By.id("accordion__panel-4");
    private static final By answerSix = By.id("accordion__panel-5");
    private static final By answerSeven = By.id("accordion__panel-6");
    private static final By answerEight = By.id("accordion__panel-7");

    //конструктор для параметризации кнопок с вопросами
    public static final String[] accordionItem = new String[]{
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"};
    //конструктор для параметризации текстов "Вопросы о важном
    public static final String[]  openPanel = new String[]{
            "accordion__panel-0",
            "accordion__panel-1",
            "accordion__panel-2",
            "accordion__panel-3",
            "accordion__panel-4",
            "accordion__panel-5",
            "accordion__panel-6",
            "accordion__panel-7"};

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    // метод нажать на кнопку куки
    public void checkCookeIsDisplayed() {
        driver.findElement(cookieButton).isDisplayed();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.className("App_CookieButton__3cvqF")));
        driver.findElement(cookieButton).click();
    }
    // метод скролла до Раздела Вопросы о важном
    public void scrollForQuestionMKAD() {
        WebElement element = driver.findElement(By.id("accordion__heading-7"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        }
    public void clickQuestionButton(String questionButtonLocator) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(questionButtonLocator)));
        driver.findElement(By.id(questionButtonLocator)).click();
    }
    //метод кликнуть по верхней кнопке заказа
    public MainPage clickButtonOrderUp(){
        driver.findElement(buttonOrderUp).click();
        return this;
    }
    ////метод кликнуть по нижней кнопке заказа
    public void clickButtonOrderDown(){
        WebElement element = driver.findElement(By.className("Button_Button__ra12g Button_Middle__1CSJM"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        driver.findElement(buttonOrderDown).click();
    }
}
