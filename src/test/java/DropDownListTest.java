import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DropDownListTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Config.pageUrl);
    }

    @AfterEach
    public void cleanUp(){
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.'",
            "1, 'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто " +
                    "сделать несколько заказов — один за другим.'",
            "2, 'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени " +
                    "аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в " +
                    "20:30, суточная аренда закончится 9 мая в 20:30.'",
            "3, 'Только начиная с завтрашнего дня. Но скоро станем расторопнее.'",
            "4, 'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.'",
            "5, 'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься " +
                    "без передышек и во сне. Зарядка не понадобится.'",
            "6, 'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.'",
            "7, 'Да, обязательно. Всем самокатов! И Москве, и Московской области.'"
    })
    public void dropList(int numberButton, String expectedAnswer) {
        HomePageQaScooter homePage = new HomePageQaScooter(driver);
        homePage.cookiesButton();
        homePage.clickQuestion(numberButton);

        By answerLocator = By.xpath(".//div[2]/p[text()='" + expectedAnswer + "']");

        String actualAnswer = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText();

        assertEquals(expectedAnswer, actualAnswer);
    }
}