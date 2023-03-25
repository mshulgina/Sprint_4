package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;



public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // локатор для формы заказа поле "Имя"
    private By name = By.cssSelector("[placeholder='* Имя']");

    // локатор для формы заказа поле "Фамилия"
    private By surname = By.cssSelector("[placeholder='* Фамилия']");

    // локатор для формы заказа поле "Адрес: куда привезти заказ"
    private By adress = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");

    // локатор для формы заказа поле "Метро"
    private By metro= By.cssSelector("[placeholder='* Станция метро']");

    // локатор для выбора станции поле "Метро"
    private By chooseMetro = By.cssSelector(".select-search__row");

    // локатор для формы заказа поле "Телефон"
    private By phone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");

    // локатор для кнопки "Далее"
    private By next = By.xpath(".//button[text()='Далее']");

    // локатор для поля "Когда привезти самокат"
    private By deliveryDate = By.cssSelector("[placeholder='* Когда привезти самокат']");

    // локатор для выбора даты из календаря
    private By dateSelection = By.cssSelector(".react-datepicker__day--025");

    // локатор для выпадающего списка "Срок аренды"
    private By rentalPeriod = By.cssSelector(".Dropdown-placeholder");

    // локатор для выбора количества дней из списка
    private By dateOption = By.xpath(".//div[text()='четверо суток']");

    // локатор для поля "Цвет самоката"
    private By colour = By.xpath(".//label[text()='серая безысходность']");

    // локатор для поля "Комментарий"
    private By commentField = By.cssSelector("[placeholder='Комментарий для курьера']");

    // локатор для кнопки "Заказать" на форме заказа
    private By orderScooter =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    // локатор для кнопки "Да" в окне сообщения
    private By buttonYes = By.xpath(".//button[text()='Да']");
    //private By buttonYes = By.className("Button_Button__ra12g Button_Middle__1CSJM");


    // локатор для поля сообщения "Заказ оформлен"
    //private By orderDecorated = By.cssSelector(".Order_ModalHeader__3FDaJ");
    private By orderDecorated = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage filling(String Firstname, String Surname, String Address,String metroStation,
                                String phoneNumber, String comment) {
        driver.findElement(name).sendKeys(Firstname);
        driver.findElement(surname).sendKeys(Surname);
        driver.findElement(adress).sendKeys(Address);
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(metroStation);
        driver.findElement(chooseMetro).click();
        driver.findElement(phone).sendKeys(phoneNumber);
        driver.findElement(next).click();
        driver.findElement(deliveryDate).click();
        driver.findElement(dateSelection).click();
        driver.findElement(rentalPeriod).click();
        driver.findElement(dateOption).click();
        driver.findElement(colour).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderScooter).click();

        return this;
    }
    public void checkStatus() throws InterruptedException {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonYes));
        new WebDriverWait(driver, 10).until(ExpectedConditions.
                elementToBeClickable(driver.findElement(By.xpath(".//button[text()='Да']"))));
        driver.findElement(buttonYes).click();
        TimeUnit.SECONDS.sleep(5);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(orderDecorated));
        String actualTextAnswer = driver.findElement(orderDecorated).getText();
        Assert.assertTrue("Текст иной", actualTextAnswer.contains("Заказ оформлен"));

    }
}
