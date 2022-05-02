package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.chrome.ChromeOptions;

class AppTest {

    private WebDriver driver;


    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/_Git Projects/App_order/driver/win/chromedriver.exe");
    }

    @BeforeEach
    public void setUp2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close () {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    void  shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id = name] input").setValue("Орлов Олег");
        form.$("[data-test-id = phone] input").setValue("+79167824318");
        form.$("[data-test-id = agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }



    @Test
    void  shouldAppearErrorMessage() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id = name] input").setValue("Orlov Oleg");
        form.$("[data-test-id = phone] input").setValue("+79167824318");
        form.$("[data-test-id = agreement]").click();
        form.$(".button__content").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
