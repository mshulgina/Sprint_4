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
    // Локаторы кнопок с вопросами
    public static final String[] accordionItem = new String[]{
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"};
    //Локаторы текстов "Вопросы о важном
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
    public MainPage checkCookeIsDisplayed(){
        if (driver.findElement(cookieButton).isDisplayed()){
            driver.findElement(cookieButton).click();
        }
        return this;
    }
    // метод скролла до Раздела Вопросы о важном
    public void scrollForQuestionMKAD() throws InterruptedException {
        WebElement element = driver.findElement(By.id("accordion__heading-7"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        }
    public void clickQuestionButton(String questionButtonLocator) throws InterruptedException {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(questionButtonLocator)));
        driver.findElement(By.id(questionButtonLocator)).click();
    }
    public MainPage clickButtonOrderUp(){
        driver.findElement(buttonOrderUp).click();
        return this;
    }
    //public void scrollButtonOrderDown() throws InterruptedException {

    //}
    public void clickButtonOrderDown(){
        WebElement element = driver.findElement(By.className("Button_Button__ra12g Button_Middle__1CSJM"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        driver.findElement(buttonOrderDown).click();
    }

}
