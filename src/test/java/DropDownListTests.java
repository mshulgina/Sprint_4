
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BaseTestClass;
import pageObjects.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DropDownListTests extends BaseTestClass {

    public WebDriver driver;
    private final String question;
    private final String answer;
    private final String answerText;

    public DropDownListTests(String question, String answer, String answerText) {
        this.question = question;
        this.answer = answer;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object[] @NotNull [] expectedAnswersParamList() {
        return new Object[][]{
                {MainPage.accordionItem[0], MainPage.openPanel[0], "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {MainPage.accordionItem[1], MainPage.openPanel[1], "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {MainPage.accordionItem[2], MainPage.openPanel[2], "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {MainPage.accordionItem[3], MainPage.openPanel[3], "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {MainPage.accordionItem[4], MainPage.openPanel[4], "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {MainPage.accordionItem[5], MainPage.openPanel[5], "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {MainPage.accordionItem[6], MainPage.openPanel[6], "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {MainPage.accordionItem[7], MainPage.openPanel[7], "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkAnswerAndQuestion() throws InterruptedException {
        MainPage objMainPage = new MainPage(driver);
        //кликнули по кнопке куки
        objMainPage.checkCookeIsDisplayed();
        //пролистали вниз до последнего вопроса МКАД
        objMainPage.scrollForQuestionMKAD();
        //кликнули по вопросу
        objMainPage.clickQuestionButton(question);
        //дождались ответа
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(answer)));
        //получили текст ответа
        String actualAnswerText = driver.findElement(By.id(answer)).getText();
        //сравнили текст ответа с тестовыми данными
        assertEquals("Иной текст", answerText, actualAnswerText);

    }
}

